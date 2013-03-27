/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author sebastian
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String group;

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.login = "";
        this.password = ""; 
        this.group = "";
        this.id = 0;
    }
    public User(String fn, String ln, String em, String lg, String pw ) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.login = lg;
        this.password = pw;    
    }
    public User(String fn, String ln, String em, String lg, String pw, String gp) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.login = lg;
        this.password = pw; 
        this.group = gp;
        this.id = 0;    
    }
    public User(int userId, String fn, String ln, String em, String lg, String pw, String gp) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.login = lg;
        this.password = pw; 
        this.group = gp;
        this.id = userId;    
    }
    public void Reset()
    {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.login = "";
        this.password = ""; 
        this.group = "";
        this.id = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
