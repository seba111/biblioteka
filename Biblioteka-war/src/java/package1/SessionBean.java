/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sebastian
 */
@ManagedBean(name="SessionBean")
@SessionScoped
public class SessionBean {
    private String username;
    private String password;
    private int count;
    

    public SessionBean() {
        this.count = 0;
        this.username = "";
        this.password = "";

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String check(){
        if(password.contentEquals("seba") && username.contentEquals("seba"))
            return "logged";
        else
        {
            return "index";
        }
    }
    public String logOut(){
        this.username = "";
        this.password = "";
        return "index";
    }

}
