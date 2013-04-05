/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.lab2.system;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the class Book.
 */
public class BookTest {
    /**
     * Tests the class Book.
     */
    @Test
    public void test() {
        Book book = new Book();
        book.addText("a");
        book.addText("b");
        assertEquals("b", book.getTextList().get(0).getValue());
    }
}
