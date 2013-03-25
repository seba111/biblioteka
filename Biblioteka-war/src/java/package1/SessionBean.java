/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;


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
public class SessionBean {
    private User currentUser;
    private List<User> users;
    

    public SessionBean() {
        this.currentUser = new User();
        this.users = new ArrayList<User>();
        this.users.add(new User("Sebastian", "Koch", "seba.k@vp.pl", "seba", "seba"));
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    public String register(){
        this.users.add(currentUser);
       // this.currentUser.Reset();
        return "main";
    }
    public String check(){      
        for(User us : users)
        {
            if(us.getLogin().contentEquals(currentUser.getLogin())&& us.getPassword().contentEquals(currentUser.getPassword()))
            {
                this.currentUser = us;
                return "logged";
            }          
        }      
        currentUser.Reset();
        return "main";      
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public String logOut(){
        this.currentUser.Reset();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "main";
    }

}
