package si.lab2.util;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Implements a recombiner that chooses a random line every iteration,
 * possibly returning the same line multiple times.
 *
 * @param <T> The type of the elements in the list
 */
public class RandomRecombiner<T> implements ListRecombiner<T> {
    /**
     * Returns a iterator for the recombined list.
     *
     * @param list The list to recombine
     * @return the iterator
     */
    @Override
    public Iterator<T> recombinedListIterator(final List<T> list) {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public T next() {
                return list.get(new Random().nextInt(list.size()));
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }

    /**
     * Returns a human-readable description of the recombiner method.
     *
     * @return the description
     */
    @Override
    public String getDescription() {
        return "Random chooser";
    }
}
