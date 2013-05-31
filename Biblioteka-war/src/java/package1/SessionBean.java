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
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.apache.commons.io.FileUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import java.util.Random;

/**
 *
 * @author sebastian
 */
@ManagedBean(name="SessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    private User currentUser;
    private News newNews;
    private ArrayList<News> newses = new ArrayList<News>();
    private ArrayList<User> users;
    private UploadedFile uploadedFile;
    private Database db;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public News getNewNews() {
        return newNews;
    }

    public void setNewNews(News newNews) {
        this.newNews = newNews;
    }
    private boolean logged;
    
    public String addNewNews(){
        InputStream inputStr = null;
        if(uploadedFile != null)
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
                this.newNews.setImage(filename);
            } 
            catch (IOException e) {
                e.printStackTrace();
            } 
            this.uploadedFile = null;
        }
        this.db.AddNews(currentUser, newNews);
        System.out.println("Nowy news: "+ newNews);
        this.newNews.Clear();
              
        this.newses = this.db.GetNewses("NEWS");
        
        for(News nn: newses){
            System.out.println(nn);
        }
        return "glowna";
    }
    public SessionBean() {
        this.currentUser = new User();
        this.newNews = new News();
        this.logged = false;
        this.db = new Database();
        this.users = db.GetUsers();
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public String register(){
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
                this.currentUser.setAvatar(filename);
            } 
            catch (IOException e) {
                e.printStackTrace();
            }                   
            this.uploadedFile = null;
        }
        this.db.AddUser(currentUser);
        this.users = db.GetUsers();
        this.currentUser.Reset();
        return "glowna";
    }
    public String check(){
        User tmp = this.db.CheckLoginData(currentUser.getLogin(), currentUser.getPassword());
        if(tmp != null)
        {
            this.currentUser = tmp;
            this.logged = true;
            return "glowna";
        } 
        this.currentUser.Reset();
        return "glowna";      
    }

    public String logOut(){
        this.currentUser.Reset();
        this.logged = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "glowna";
    }

    public ArrayList<News> getNewses() {
        return newses;
    }

    public void setNewses(ArrayList<News> newses) {
        this.newses = newses;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    public void RefreshCurrentUser(){
        User tmp = db.RefreshUser(this.currentUser.getId());
        this.currentUser = tmp;
    }
        public String editAction(User usr){
        usr.setEditable(true);
        return "editUsers";
    }
    public String saveAction(User usr){
        usr.setEditable(false);
        db.UpdateUser(usr);
        this.users = db.GetUsers();
        this.RefreshCurrentUser();
        return "editUsers";
    }
}
