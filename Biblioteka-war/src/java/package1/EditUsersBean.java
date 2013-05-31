/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

/**
 *
 * @author sebastian
 */
@ManagedBean(name = "EditUsersBean")
@ApplicationScoped
public class EditUsersBean implements Serializable{
    private static final long serialVersionUID = 1L;
    private ArrayList<User> users;
    private Database db;
    
    public EditUsersBean()
    {
        db = new Database();
        this.users = db.GetUsers();
    }

    public ArrayList<User> getUsers() {
        
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    public String editAction(User usr){
        usr.setEditable(true);
        return "editUsers";
    }
    public String saveAction(User usr){
        usr.setEditable(false);
        db.UpdateUser(usr);
        this.users = db.GetUsers();
        return "editUsers";
    }
    
}
