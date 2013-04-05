package si.lab2.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implements a recombiner that puts the elements in a random order.
 *
 * @param <T> The type of the elements in the list
 */
public class PermuteRecombiner<T> implements ListRecombiner<T> {

    /**
     * Returns a iterator for the recombined list.
     *
     * @param list The list to recombine
     * @return the iterator
     */
    @Override
    public Iterator<T> recombinedListIterator(List<T> list) {
        ArrayList<T> shuffled = new ArrayList<T>(list);
        Collections.shuffle(shuffled);
        return shuffled.iterator();
    }

    /**
     * Returns a human-readable description of the recombiner method.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Random permutation";
    }
}
