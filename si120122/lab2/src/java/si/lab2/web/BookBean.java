package si.lab2.web;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import si.lab2.system.Book;

@Named("bookBean")
@ApplicationScoped
public class BookBean implements Serializable {
    private Book book;
    
    public BookBean() {
        book = new Book();
    }

    public Book getBook() {
        return book;
    }

}
