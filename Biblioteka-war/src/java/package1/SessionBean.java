/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

}
