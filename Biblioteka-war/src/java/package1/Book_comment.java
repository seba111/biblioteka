/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author sebastian
 */
public class Book_comment {
    private Integer id;
    private User usr;
    private String created_at;
    private String content;
    
    public Book_comment(){
        this.id = 0;
        this.usr = null;
        this.created_at = null;
        this.content = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book_comment(Integer id, User usr, String created_at, String content) {
        this.id = id;
        this.usr = usr;
        this.created_at = created_at;
        this.content = content;
    }
    
    
}
