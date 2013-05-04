package lab3;

import java.util.HashMap;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

class KeywordCountTableModel extends AbstractTableModel implements KeywordCounter {

    private String[] keywords = new String[]{"abstract", "continue", "for",
        "new", "switch", "assert", "default", "if", "package", "synchronized",
        "boolean", "do", "goto", "private", "this", "break", "double",
        "implements", "protected", "throw", "byte", "else", "import", "public",
        "throws", "case", "enum", "instanceof", "return", "transient", "catch",
        "extends", "int", "short", "try", "char", "final", "interface",
        "static", "void", "class", "finally", "long", "strictfp", "volatile",
        "const", "float", "native", "super", "while"};
    private int nkeyword = keywords.length;
    private int[] counts = new int[nkeyword];
    private Map<String, Integer> idof;

    public KeywordCountTableModel() {
        idof = new HashMap<String, Integer>();
        for (int i = 0; i < nkeyword; i++) {
            idof.put(keywords[i], i);
        }
    }

    @Override
    public synchronized boolean countWord(String word) {
        if (idof.containsKey(word)) {
            counts[idof.get(word)]++;
            fireTableDataChanged();
            return true;
        }
        return false;
    }

    @Override
    public synchronized void clear() {
        for (int i = 0; i < nkeyword; i++) {
            counts[i] = 0;
        }
        fireTableDataChanged();
    }

    @Override
    public synchronized int getRowCount() {
        return nkeyword;
    }

    @Override
    public synchronized int getColumnCount() {
        return 2;
    }

    @Override
    public synchronized Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return keywords[rowIndex];
        }
        return counts[rowIndex];
    }
}
