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
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.login = "";
        this.password = ""; 
    }
    public User(String fn, String ln, String em, String lg, String pw) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.login = lg;
        this.password = pw; 
    }
    public void Reset()
    {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.login = "";
        this.password = ""; 
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
