/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

/**
 *
 * @author slimocb
 */
public class NewsComment {
    private Integer id;
    private Integer user_id;
    private Integer news_id;
    private String created_at;
    private String content ;

    
    public NewsComment(){
        this.id = 0;
        this.user_id = 0 ;
        this.news_id = 0 ;
        this.created_at = null;
        this.content = null ;
    }
    
    
    public NewsComment(Integer id , Integer user_id, Integer news_id, String created_at, String content){
        this.id = id ;
        this.user_id = user_id ;
        this.news_id = news_id ;
        this.created_at = created_at ;
        this.content = content ;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
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
    
    
    @Override
    public String toString(){
        return this.content ;
    }
}
