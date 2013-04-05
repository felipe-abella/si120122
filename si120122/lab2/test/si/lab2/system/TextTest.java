package si.lab2.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the class Text.
 */
public class TextTest {
    private Text text;
    private List<String> textWords;
    private static final int textNLine = 12;
    
    /**
     * Prepare the tests.
     */
    @Before
    public void setUp() {
        text = new Text();
        
        textWords = new ArrayList<String>();
        String newValue = "";
        for (int line = 0; line < textNLine; line++)
            for (int id = 0; id < Text.LINE_SIZE; id++) {
                String word = "L" + line + "W" + id;
                textWords.add(word);
                newValue += word + " ";
            }
                
        
        text.setValue(newValue);
    }
    
    /**
     * Tests the method getFirstWords.
     */
    @Test
    public void testGetFirstWords() {
        for (int i = 0; i <= textWords.size(); i++)
            assertEquals(textWords.subList(0, i), text.getFirstWords(i));
        assertEquals(textWords.subList(0, textWords.size()), text.getFirstWords(textWords.size()+1));
        assertEquals(textWords.subList(0, textWords.size()), text.getFirstWords(textWords.size()+2));
    }
    
    /**
     * Tests the IllegalArgumentException raised in getFirstWords method.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetFirstWordsException() {
        text.getFirstWords(-1);
    }
    
    /**
     * Tests the getLine method.
     */
    @Test
    public void testGetLine() {
        for (int i = 0; i < textNLine; i++)
            assertEquals(textWords.subList(i*Text.LINE_SIZE, (i+1)*Text.LINE_SIZE),
                    text.getLine(i));
        assertEquals(null, text.getLine(textNLine));
        
        List<String> expected = new ArrayList<String>();
        expected.add("bla");
        text.setValue(text.getValue() + " bla");
        
        assertEquals(expected, text.getLine(textNLine));
    }
    
    /**
     * Tests the breakInWords method.
     */
    @Test
    public void testBreakInWords() {
        List<String> expected = new ArrayList<String>();
        
        expected.add("bla");
        assertEquals(expected, Text.breakInWords("bla"));
        assertEquals(expected, Text.breakInWords("bla   "));
        assertEquals(expected, Text.breakInWords("  bla"));
        assertEquals(expected, Text.breakInWords("  bla  "));
        
        expected.add("test");
        assertEquals(expected, Text.breakInWords("bla   test"));
        assertEquals(expected, Text.breakInWords("  bla   test"));
        assertEquals(expected, Text.breakInWords("  bla  test  "));
        assertEquals(expected, Text.breakInWords("bla test  "));
        
        expected.add("hey");
        assertEquals(expected, Text.breakInWords("bla   test hey"));
        assertEquals(expected, Text.breakInWords("  bla   test   hey"));
        assertEquals(expected, Text.breakInWords("  bla  test  hey    "));
        assertEquals(expected, Text.breakInWords("bla test  hey"));
        
        expected.add("ah,");
        assertEquals(expected, Text.breakInWords("bla   test hey ah,"));
        assertEquals(expected, Text.breakInWords("  bla   test   hey ah,   "));
        assertEquals(expected, Text.breakInWords("  bla  test  hey    ah,"));
        assertEquals(expected, Text.breakInWords("bla test  hey    ah,   "));
        
        expected.add("How !");
        assertEquals(expected, Text.breakInWords("bla   test hey ah, How !"));
        assertEquals(expected, Text.breakInWords("  bla   test   hey ah,  How !"));
        assertEquals(expected, Text.breakInWords("  bla  test  hey    ah, How !"));
        assertEquals(expected, Text.breakInWords("bla test  hey    ah,   How !"));
        
        expected.add("Hey  !! !   !!!");
        assertEquals(expected, Text.breakInWords("bla   test hey ah, How ! Hey  !! !   !!!"));
        assertEquals(expected, Text.breakInWords("  bla   test   hey ah,  How !   Hey  !! !   !!!"));
        assertEquals(expected, Text.breakInWords("  bla  test  hey    ah, How ! Hey  !! !   !!!    "));
        assertEquals(expected, Text.breakInWords("bla test  hey    ah,   How !  Hey  !! !   !!!   "));
        
        expected.add("bl4");
        assertEquals(expected, Text.breakInWords("bla   test hey ah, How ! Hey  !! !   !!! bl4"));
        assertEquals(expected, Text.breakInWords("  bla   test   hey ah,  How !   Hey  !! !   !!!   bl4"));
        assertEquals(expected, Text.breakInWords("  bla  test  hey    ah, How ! Hey  !! !   !!! bl4   "));
        assertEquals(expected, Text.breakInWords("bla test  hey    ah,   How !  Hey  !! !   !!!   bl4 "));
       
        expected.add(0, "!  !");
        assertEquals(expected, Text.breakInWords("!  !  bla test  hey    ah,   How !  Hey  !! !   !!!   bl4 "));
        assertEquals(expected, Text.breakInWords("  !  !   bla test  hey    ah,   How !  Hey  !! !   !!!   bl4 "));
        assertEquals(expected, Text.breakInWords("  !  !bla test  hey    ah,   How !  Hey  !! !   !!!   bl4 "));
        assertEquals(expected, Text.breakInWords("!  !bla test  hey    ah,   How !  Hey  !! !   !!!   bl4 "));
    }
}
