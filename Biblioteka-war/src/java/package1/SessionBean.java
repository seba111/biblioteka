/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;


import java.io.Serializable;
import java.util.ArrayList;
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
    

    public SessionBean() {
        this.currentUser = new User();
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
            currentUser = tmp;
            return "logged";
        } 
        currentUser.Reset();
        return "main";      
    }

    public String logOut(){
        this.currentUser.Reset();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "main";
    }

}
