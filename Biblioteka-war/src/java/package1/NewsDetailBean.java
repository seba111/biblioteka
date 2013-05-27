/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author slimocb
 */
@ManagedBean(name = "NewsDetailBean")
@RequestScoped
public class NewsDetailBean implements Serializable {

    private News news ;
    @ManagedProperty("#{param.id}")
    private String id;
    public NewsDetailBean() {
        
    }
    
 
    
    public void setNews(News parameter){
        this.news = parameter;
    }
    
    public News getNews(){
        Database db = new Database();
        this.news = db.getNews(this.id);
        return this.news;
    }
    
    public void setId(String mojget){
        this.id = mojget ;
    }
    
    public String getId(){
        return this.id;
    }
}
