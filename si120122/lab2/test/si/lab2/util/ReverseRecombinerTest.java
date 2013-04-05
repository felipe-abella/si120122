package si.lab2.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the ReverseRecombiner class.
 */
public class ReverseRecombinerTest {
   
    private static <T> List<T> createList(Iterator<T> iterator) {
        ArrayList<T> list = new ArrayList<T>();
        
        while (iterator.hasNext())
            list.add(iterator.next());
        
        return list;
    }
    
    /**
     * Tests the recombinedListIterator method.
     */
    @Test
    public void testRecombinedListIterator() {
        ListRecombiner recombiner = new ReverseRecombiner();
        
        List<Integer> list = new LinkedList<Integer>();
        List<Integer> reversed = new LinkedList<Integer>();
        
        for (int i = 1; i <= 10; i++) {
            list.add(i);
            reversed.add(0, i);
            
            assertEquals(reversed, createList(recombiner.recombinedListIterator(list)));
        }
    }
}
