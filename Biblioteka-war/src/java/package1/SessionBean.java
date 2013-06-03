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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
        return "glowna";
    }
    public SessionBean() {
        this.currentUser = new User();
        this.newNews = new News();
        this.logged = false;
    }

    public boolean isLogged() {
        
        /*try{
            Cookie loggedCookie = (Cookie) FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap().get("logged");
            if(loggedCookie != null){
                if("activeUser".equals(loggedCookie.getValue())){
                    logged = true;
                }
                else{
                    logged = false ;
                }
            }
        }
        catch(Exception e){
            
        }*/
        
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
            
            Cookie logCookie = null;
            
            try{
                HttpServletResponse response;
                response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                logCookie = new Cookie("logged", "activeUser");
                response.addCookie(logCookie);
            }
            catch(Exception e){
                
            }
            
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

}
