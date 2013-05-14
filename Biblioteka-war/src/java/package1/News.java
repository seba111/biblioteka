/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author sebastian
 */
public class News {
    private Integer id;
    private String title;
    private String content;
    private String created_at;
    private String user_id;
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public News(){
        this.id = 0;
        this.title = "";
        this.content = "";
        this.created_at = "";
        this.user_id = "";
        this.category = "";
    }

    public News(Integer id, String title, String content, String created_at, String user_id, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.user_id = user_id;
        this.category = category;
    }
    public void Clear(){
        this.id = 0;
        this.title = "";
        this.content = "";
        this.created_at = "";
        this.user_id = "";
        this.category = "";       
    }

    public News(String title, String content, String user_id, String category) {
        this.title = title;
        this.content = content;
        this.user_id = user_id;
        this.category = category;
    }
    @Override
    public String toString(){
        return this.title + " " +this.content;
    }
}
