package si.lab2.util;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implements a recombiner that puts the elements in reverse order.
 *
 * @param <T> The type of the elements in the list
 */
public class ReverseRecombiner<T> implements ListRecombiner<T> {

    /**
     * Returns a iterator for the recombined list.
     *
     * @param list The list to recombine
     * @return the iterator
     */
    @Override
    public Iterator<T> recombinedListIterator(final List<T> list) {
        return new Iterator<T>() {
            private int index = list.size();

            @Override
            public boolean hasNext() {
                return index - 1 >= 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return list.get(--index);
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
        return "Reverse order";
    }
}
