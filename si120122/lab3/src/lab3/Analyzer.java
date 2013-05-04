package lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Class responsible for analyzing ".java"s in a directory.
 */
public class Analyzer {

    private final int HARD_THREAD_MAX = 25;
    private ExecutorService executor;
    private int nworking = 0;
    private KeywordCounter counter;
    private final List<AnalyzerListener> listeners;
    private boolean running = false;
    private long startTime = 0, elapsedTime = 0;
    private int ndirfound = 0, nfound = 0, nanalyzed = 0;

    /**
     * Creates a new analyzer.
     *
     * @param counter the keyword counter
     */
    public Analyzer(KeywordCounter counter) {
        this.counter = counter;
        listeners = new ArrayList<AnalyzerListener>();
    }

    /**
     * Clears the analyzers.
     *
     * The analyzer must not be running when this is called
     */
    public synchronized void clear() {
        if (running) {
            throw new IllegalStateException("Cant clear Analyzer when running");
        }
        counter.clear();
        startTime = elapsedTime = 0;
        nfound = nanalyzed = 0;
    }

    /**
     * Returns if the analyzer is running.
     *
     * @return if the analyzer is running.
     */
    public synchronized boolean isRunning() {
        return running;
    }

    /**
     * Adds a new analyzer listener.
     *
     * @param listener the listener
     */
    public void addListener(AnalyzerListener listener) {
        listeners.add(listener);
    }

    /**
     * Returns the number of files found.
     *
     * @return the number of files found
     */
    public synchronized int getNfound() {
        return nfound;
    }

    /**
     * Returns the number of directories found.
     *
     * @return the number of directories found
     */
    public synchronized int getNdirfound() {
        return ndirfound;
    }

    /**
     * Returns the number of files analyzed.
     *
     * @return
     */
    public synchronized int getNanalyzed() {
        return nanalyzed;
    }

    private void processFile(File file) {
        try {
            if (file.getName().endsWith(".java")) {
                Scanner scn = new Scanner(file);
                while (scn.hasNext()) {
                    String word = scn.next();
                    counter.countWord(word);
                }
                synchronized (this) {
                    nanalyzed++;
                }
                scn.close();
            }
        } catch (FileNotFoundException ex) {
        } finally {
            long endTime = System.currentTimeMillis();
            synchronized (this) {
                elapsedTime = endTime - startTime;
            }
            synchronized (listeners) {
                for (AnalyzerListener listener : listeners) {
                    listener.newFileFound();
                }
            }
        }

        synchronized (this) {
            nworking--;
            if (nworking == 0) {
                new Thread() {
                    @Override
                    public void run() {
                        finished();
                    }
                }.start();
            }
        }
    }

    private void processDir(File dir) {
        for (final File child : dir.listFiles()) {
            if (child.isDirectory()) {
                synchronized (this) {
                    ndirfound++;
                    nworking++;
                }
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        processDir(child);
                    }
                });

            } else if (child.isFile()) {
                synchronized (this) {
                    nfound++;
                    nworking++;
                }
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        processFile(child);
                    }
                });
            }
        }

        synchronized (this) {
            nworking--;
            if (nworking == 0) {
                new Thread() {
                    @Override
                    public void run() {
                        finished();
                    }
                }.start();
            }
        }
    }

    private synchronized void finished() {
        executor.shutdown();

        while (!executor.isShutdown()) {
            try {
                executor.awaitTermination(1, TimeUnit.DAYS);
            } catch (InterruptedException ex) {
            }
        }

        elapsedTime = System.currentTimeMillis() - startTime;
        running = false;
        synchronized (listeners) {
            for (AnalyzerListener listener : listeners) {
                listener.analyzingFinished();
            }
        }
    }

    /**
     * Returns the analyzing total time.
     *
     * @return the elapsed time
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Starts the analyzing.
     *
     * @param dirName The directory name
     * @param maxthread The maximum number of threads
     * @throws IllegalArgumentException If dirName is not a directory, or
     * maxthread is negative
     */
    public void start(String dirName, int maxthread) throws IllegalArgumentException {
        final File dir = new File(dirName);

        clear();

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

        /* Avoid freezing your computer! */
        if (maxthread == 0 || maxthread > HARD_THREAD_MAX) {
            maxthread = HARD_THREAD_MAX;
            /* If you want to be able to use an unlimited number of threads,
             * comment this at your own risk! */
        }

        if (maxthread > 0) {
            executor = Executors.newFixedThreadPool(maxthread);
        } else {
            executor = Executors.newCachedThreadPool();
        }

        startTime = System.currentTimeMillis();

        executor.submit(new Runnable() {
            @Override
            public void run() {
                nworking = 1;
                processDir(dir);
            }
        });
    }
}
