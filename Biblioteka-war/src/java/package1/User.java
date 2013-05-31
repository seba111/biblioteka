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
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String created_at;
    private String login;
    private String password;
    private String avatar;
    private Integer status;
    private boolean editable;

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.login = "";
        this.password = ""; 
        this.avatar = "empty-avatar.png"; 
        this.status = 0;
        this.id = 0;
        this.editable = false;
    }
    public User(String fn, String ln, String em, String lg, String pw ) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.login = lg;
        this.password = pw;    
    }
    public User(String fn, String ln, String em, String at, String lg, String pw, String gp) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.created_at = at;
        this.login = lg;
        this.password = pw; 
        this.status = 0;
        this.id = 0;    
    }
    public User(Integer id, String email,String first_name, String last_name, String created_at, String login, String password, String avatar, Integer status) {
        this.id = id;
        this.email = email;
        this.firstName = first_name;
        this.lastName = last_name;
        this.created_at = created_at;
        this.login = login;
        this.password = password; 
        this.status = status;
        this.avatar = avatar;
           
    }
    public void Reset()
    {
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.login = "";
        this.password = "";
        this.created_at ="";
        this.avatar = "empty-avatar.png";
        this.status = 0;
        this.id = 0;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    @Override
    public String toString(){
        return this.firstName + " "+ this.lastName;
    }
}
