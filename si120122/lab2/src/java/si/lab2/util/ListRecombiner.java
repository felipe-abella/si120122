package si.lab2.util;

import java.util.Iterator;
import java.util.List;

/**
 * Interface implemented by classes able to receive a list and recombine its
 * elements in another, well-defined, manner.
 * 
 * @param <T> The type of the elements in the list.
 */
public interface ListRecombiner<T> {
    /**
     * Returns a iterator for the recombined list.
     * 
     * @param list The list to recombine
     * @return the iterator
     */
    public Iterator<T> recombinedListIterator(List<T> list);
    
    /**
     * Returns a human-readable description of the recombiner method.
     * 
     * @return the description
     */
    public String getDescription();
}
