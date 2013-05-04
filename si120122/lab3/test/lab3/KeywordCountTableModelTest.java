package lab3;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the KeywordCountTableModel class.
 */
public class KeywordCountTableModelTest {

    public KeywordCountTableModelTest() {
    }

    /**
     * Tests the KeywordCountTableModel class.
     */
    @Test
    public void test() {
        KeywordCountTableModel model = new KeywordCountTableModel();

        model.clear();
        model.countWord("abstract");
        model.countWord("bla");
        assertEquals(1, model.getValueAt(0, 1));
        model.countWord("abstract");
        assertEquals(2, model.getValueAt(0, 1));

        model.clear();
        assertEquals(0, model.getValueAt(0, 1));
    }
}