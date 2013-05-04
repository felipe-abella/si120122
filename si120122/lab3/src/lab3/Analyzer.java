package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Analyzer {

    private long startTime = 0, elapsedTime = 0;
    private int nfound = 0, nanalyzed = 0;
    private boolean running = false;
    private KeywordCounter counter;
    private List<AnalyzerListener> listeners;

    public Analyzer(KeywordCounter counter) {
        this.counter = counter;
        listeners = new ArrayList<AnalyzerListener>();
    }

    public synchronized void clear() {
        if (running) {
            throw new IllegalStateException("Cant clear Analyzer when running");
        }
        startTime = elapsedTime = 0;
        nfound = nanalyzed = 0;
    }

    public boolean isRunning() {
        return running;
    }

    public void addListener(AnalyzerListener listener) {
        listeners.add(listener);
    }

    public int getNfound() {
        return nfound;
    }

    public int getNanalyzed() {
        return nanalyzed;
    }

    private void processFile(File file) {
        nfound++;

        try {
            if (file.getName().endsWith(".java")) {
                Scanner scn = new Scanner(file);
                while (scn.hasNext()) {
                    String word = scn.next();
                    counter.countWord(word);
                }
                nanalyzed++;
                scn.close();
            }
        } catch (FileNotFoundException ex) {
        } finally {
            long endTime = System.currentTimeMillis();
            synchronized (this) {
                elapsedTime = endTime - startTime;
            }
            for (AnalyzerListener listener : listeners) {
                listener.newFileFound();
            }
        }
    }

    private void processDir(File dir) {
        for (File child : dir.listFiles()) {
            if (child.isDirectory()) {
                processDir(child);
            } else if (child.isFile()) {
                processFile(child);
            }
        }
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void start(String dirName, int maxthread) throws IllegalArgumentException {
        final File dir = new File(dirName);

        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("\"" + dirName + "\" is not a directory!");
        }
        if (maxthread < 0) {
            throw new IllegalArgumentException("Invalid amount of threads!");
        }

        running = true;
        for (AnalyzerListener listener : listeners) {
            listener.analyzingStarted();
        }

        new Thread() {
            @Override
            public void run() {
                startTime = System.currentTimeMillis();
                processDir(dir);

                elapsedTime = System.currentTimeMillis() - startTime;

                running = false;
                for (AnalyzerListener listener : listeners) {
                    listener.analyzingFinished();
                }
            }
        }.start();
    }
}
