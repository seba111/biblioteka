/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Random;
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
    private UploadedFile uploadedFile;
    public AddBookBean(){
        book = new Book();
        uploadedFile = null;
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
        
        Database db = new Database();
        db.AddBook(book);
        return "glowna";
    }   
}
