/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
public class Book {
    private Integer id;
    private String image;
    private String title;
    private String description;
    private String year;
    private String autor;

    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }
    private String rented ;
    private ArrayList<Book_comment> comments;
    
    public Book(){
        this.id = 0;
        this.image = null;
        this.title = null;
        this.description = null;
        this.year = null;
        this.autor = null;
        this.comments = null;   
        this.rented = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ArrayList<Book_comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Book_comment> comments) {
        this.comments = comments;
    }

    public Book(Integer id, String image, String title, String description, String year, String autor, ArrayList<Book_comment> comments) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.year = year;
        this.autor = autor;
        this.comments = comments;
    }

    public Book(Integer id, String image, String title, String description, String year, String autor, String rented) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
        this.year = year;
        this.autor = autor;
        this.rented = rented ;
    }
    
    
    
    
}
