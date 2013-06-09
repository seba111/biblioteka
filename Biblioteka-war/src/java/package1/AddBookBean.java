/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sebastian
 */
@ManagedBean(name = "AddBookBean")
@RequestScoped
public class AddBookBean implements Serializable{
    private Book book;
    
    private String id;
    public AddBookBean(){
        book = new Book();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    public String addBook(){
        Database db = new Database();
        db.AddBook(book);
        return "glowna";
    }   
}
