package si.lab2.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import si.lab2.system.SizeRecombiner;
import si.lab2.system.Text;
import si.lab2.util.ListRecombiner;
import si.lab2.util.PermuteRecombiner;
import si.lab2.util.RandomRecombiner;
import si.lab2.util.ReverseRecombiner;

@Named("recombinerBean")
@SessionScoped
public class RecombinerBean implements Serializable {
    private String originalText;
    private String recombinedText;
    private ListRecombiner<String> recombiner;
    private List<ListRecombiner<String>> recombiners;
    private Iterator<String> recombinerIterator;
    
    public RecombinerBean() {
        recombiners = new ArrayList<ListRecombiner<String>>();
        recombiners.add(new ReverseRecombiner<String>());
        recombiners.add(new RandomRecombiner<String>());
        recombiners.add(new PermuteRecombiner<String>());
        recombiners.add(new SizeRecombiner());
        
        recombinerIterator = null;
    }

    public String getOriginalText() {
        return originalText;
    }
    
    public String getFormattedOriginalText() {
        Text text = new Text(originalText);
        String result = "";
        for (int i = 0; i < text.countLines(); i++)
            result += text.getStringLine(i) + "\n";
        return result;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getRecombinedText() {
        return recombinedText;
    }

    public List<ListRecombiner<String>> getRecombiners() {
        return recombiners;
    }

    public String getRecombiner() {
        if (recombiner == null)
            return "";
        return recombiner.getDescription();
    }

    private ListRecombiner<String> findRecombiner(String description) {
        for (ListRecombiner<String> recomb: recombiners)
            if (recomb.getDescription().equals(description))
                return recomb;
        return null;
    }
    
    public void setRecombiner(String recombiner) {
        this.recombiner = findRecombiner(recombiner);
    }
    
    public boolean getHasAnotherLine() {
        return recombinerIterator != null && recombinerIterator.hasNext();
    }
    
    public String addAnotherLine() {
        if (getHasAnotherLine())
            recombinedText += recombinerIterator.next() + "\n";
        return "";
    }
    
    public String startRecombining() {
        recombinedText = "";
        recombinerIterator = recombiner.recombinedListIterator(new Text(originalText).getStringLines());
        return "recombineText?faces-redirect=true";
    }
}
