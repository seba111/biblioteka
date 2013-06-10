/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

/**
 *
 * @author sebastian
 */
@ManagedBean(name = "AddBookBean")
@RequestScoped
public class AddBookBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private Book book;
    private ArrayList<Book> books ;
    private ArrayList<Book> userBooks ;
    private Database db;

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
    private UploadedFile uploadedFile;
    public AddBookBean(){
        book = new Book();
        books = new ArrayList<Book>();
        uploadedFile = null;
        db = new Database();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public ArrayList<Book> getUserBooks() {
        Database db = new Database();
        this.userBooks = db.GetUserBooks(1);
        return userBooks;
    }

    public void setUserBooks(ArrayList<Book> userBooks) {
        this.userBooks = userBooks;
    }

    
    
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public String addBook(){
        InputStream inputStr = null;
        if(this.uploadedFile != null)
        {
            try {
                inputStr = uploadedFile.getInputStream();       

                String relativeWebPath = "/img/";
                String absoluteDiskPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativeWebPath);//externalContext.getRealPath(relativeWebPath);
                //generowanie randomowego stringa
                char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 35; i++) {
                    char c = chars[random.nextInt(chars.length)];
                    sb.append(c);
                }
                String output = sb.toString();
                
                // nazwa pliku to randomowy string + rozszerzenie z uploadowanego pliku
                String filename = output+uploadedFile.getName().substring(uploadedFile.getName().lastIndexOf("."));
                File file = new File(absoluteDiskPath+"/../../../web/img/"+filename);
                if(!file.exists())
                {
                    System.out.println("nie istnieje");
                    file.createNewFile();
                }
                else
                {
                    System.out.println("juz istnieje");
                }

                FileUtils.copyInputStreamToFile(inputStr, file);
                this.book.setImage(filename);
            }
            catch (IOException e) {
                e.printStackTrace();
            }                   
            this.uploadedFile = null;
        }
        
        db.AddBook(book);       
        return "glowna";
    }
    
    
    public String searchBooks(){
        Database db = new Database();
        this.books = db.GetBooks(this.book);
        //db.Close();
        return "searchresult";        
    }
    
    public String rent(){
        Database db = new Database();
        int user_id = (Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenUserId")));
        int book_id = (Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenBookId")));
        db.rentBook(user_id,book_id);
        this.books = db.GetBooks(book);
       
        return "searchresult";
    }
 }
