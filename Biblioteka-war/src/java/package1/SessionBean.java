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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.FileUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;

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
    private UploadedFile uploadedFile;

    public News getNewNews() {
        return newNews;
    }

    public void setNewNews(News newNews) {
        this.newNews = newNews;
    }
    private boolean logged;
    
    public String addNewNews(){
        Database db = new Database();
        db.AddNews(currentUser, newNews);
        System.out.println("Nowy news: "+ newNews);
        newNews.Clear();
              
        newses = db.GetNewses("NEWS");
        
        for(News nn: newses){
            System.out.println(nn);
        }
        return "main";
    }
    public SessionBean() {
        this.currentUser = new User();
        this.newNews = new News();
        this.logged = false;
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
        try {
            inputStr = uploadedFile.getInputStream();       
            
            String relativeWebPath = "/img/";
            String absoluteDiskPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativeWebPath);//externalContext.getRealPath(relativeWebPath);
            
         //   File dir = new File("./images/");
            File file = new File(absoluteDiskPath+"/"+uploadedFile.getName());
        //    if(!dir.exists())
        //    {                 
        //        dir.mkdirs();
         //   }
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
            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        
        this.currentUser.setAvatar(uploadedFile.getName());
        
        
        Database db = new Database();
        db.AddUser(currentUser);
        this.currentUser.Reset();
        return "main";
    }
    public String check(){
        Database db = new Database();
        User tmp = db.CheckLoginData(currentUser.getLogin(), currentUser.getPassword());
        if(tmp != null)
        {
            this.currentUser = tmp;
            this.logged = true;
            return "main";
        } 
        this.currentUser.Reset();
        return "main";      
    }

    public String logOut(){
        this.currentUser.Reset();
        this.logged = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "main";
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

}
