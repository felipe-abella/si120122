package si.lab2.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import si.lab2.util.ListRecombiner;

/**
 * Implements a recombiner that chooses smaller lines first.
 */
public class SizeRecombiner implements ListRecombiner<String> {

    /**
     * Returns a iterator for the recombined list.
     *
     * @param list The list to recombine
     * @return the iterator
     */
    @Override
    public Iterator<String> recombinedListIterator(List<String> list) {
        ArrayList<String> recombined = new ArrayList<String>(list);
        Collections.sort(recombined, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        return recombined.iterator();
    }

    /**
     * Returns a human-readable description of the recombiner method.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Smaller line first";
    }
}
