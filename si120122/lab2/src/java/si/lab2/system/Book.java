package si.lab2.system;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private List<Text> textList;
    
    public Book() {
        textList = new ArrayList<Text>();
    }

    public List<Text> getTextList() {
        return textList;
    }

    public void setTextList(List<Text> textList) {
        this.textList = textList;
    }
    
    public void addText(String textValue) {
        textList.add(0, new Text(textValue));
    }
}
