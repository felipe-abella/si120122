package si.lab2.system;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a Text of the user.
 */
public class Text {

    public static final int LINE_SIZE = 10;
    public static final int PREVIEW_SIZE = 12;
    public String value;
    public List<String> words;

    /**
     * Constructs a new, empty, Text.
     */
    public Text() {
        value = "";
        words = breakInWords(value);
    }

    /**
     * Constructs a new Text with a given value.
     *
     * @param value The text's value
     */
    public Text(String value) {
        setValue(value);
    }

    /**
     * Returns the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
        words = breakInWords(value);
    }

    /**
     * Returns a list of the first words in the text.
     *
     * @param howMany How many words (at most) should be returned
     * @return a list of the words
     */
    public List<String> getFirstWords(int howMany) {
        if (howMany < 0) {
            throw new IllegalArgumentException("\"HowMany\" can't be negative.");
        }
        return new ArrayList<String>(words.subList(0, Math.min(words.size(), howMany)));
    }

    /**
     * Returns a preview containing at most PREVIEW_SIZE words.
     *
     * @return the preview
     */
    public String getPreview() {
        if (words.size() <= PREVIEW_SIZE) {
            return value;
        }

        String result = "";
        for (String word : getFirstWords(PREVIEW_SIZE)) {
            result += word + " ";
        }
        result += "...";

        return result;
    }

    /**
     * Return the amount of lines this text have.
     *
     * @return the amount of lines
     */
    public int countLines() {
        return (words.size() + LINE_SIZE - 1) / LINE_SIZE;
    }

    /**
     * Returns the lineIndex-th (0-based) line of the text.
     *
     * Each line of the text is made of LINE_SIZE contiguous words, such that
     * the first line contains the first LINE_SIZE words of the text. The last
     * line is an exception and might contain less than LINE_SIZE words.
     *
     * @param lineIndex the index of the line to return
     * @return the line, or null if the index is out of permitted bounds
     */
    public List<String> getLine(int lineIndex) {
        if (lineIndex < 0 || lineIndex * LINE_SIZE >= words.size()) {
            return null;
        }
        return new ArrayList<String>(words.subList(lineIndex * LINE_SIZE,
                Math.min((lineIndex + 1) * LINE_SIZE, words.size())));
    }

    /**
     * Returns "getLine(lineIndex)" converted to a space-separated list of
     * words.
     *
     * @param lineIndex The index of the line
     * @return the space-separated list of words
     */
    public String getStringLine(int lineIndex) {
        List<String> line = getLine(lineIndex);
        String result = "";

        if (line == null) {
            return null;
        }

        for (int i = 0; i < line.size(); i++) {
            result += line.get(i);
            if (i + 1 < line.size()) {
                result += " ";
            }
        }

        return result;
    }

    /**
     * Breaks a text into a list of words.
     *
     * Contiguous block of letters and digits will be divided on different
     * words, but punctuation symbols will be included in the closest word to
     * the left, or the right word if they come on the start of the text.
     *
     * Spaces between a block and a punctuation symbol will be preserved, but
     * spaces between different words will be trimmed. See testBreakInWords
     * method of TextTest for a detailed specification of this.
     *
     * @param value The value of the text to be broken into words
     * @return A list of words
     */
    public static List<String> breakInWords(String value) {
        List<String> result = new ArrayList<String>();

        int wordStart = 0, wordEnd = 0;
        for (int i = 0; i <= value.length(); i++) {
            if (i == value.length() || (wordEnd != i && Character.isLetterOrDigit(value.charAt(i)))) {
                wordEnd = i;
                String newWord = value.substring(wordStart, wordEnd).trim();
                if (!newWord.isEmpty()) {
                    result.add(newWord);
                }

                wordStart = i;
                wordEnd = i + 1;
            } else if (Character.isLetterOrDigit(value.charAt(i))) {
                wordEnd = i + 1;
            }
        }

        return result;
    }

    /**
     * Returns a list of a string representation of each line.
     *
     * Each string representation is calculated with getStringLine(i).
     *
     * @return the list
     */
    public List<String> getStringLines() {
        List<String> result = new ArrayList<String>();
        
        for (int i = 0; i < countLines(); i++) {
            result.add(getStringLine(i));
        }
        
        return result;
    }
    
    /**
     * Return the text formatted, i.e., with "\n"s between lines.
     * 
     * @return the text formatted
     */
    public String getFormatted() {
        String result = "";
        for (int i = 0; i < countLines(); i++)
            result += getStringLine(i) + "\n";
        return result;
    }
}
