/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

/**
 *
 * @author slimocb
 */
@ManagedBean(name = "NewsDetailBean")
@RequestScoped
public class NewsDetailBean implements Serializable {

    private News news ;
    private NewsComment addNews ;
    private ArrayList<NewsComment> comments;

    public ArrayList<NewsComment> getComments() {
        Database db = new Database();
        this.comments = db.GetComments(this.id );
        return this.comments;
    }

    public void setComments(ArrayList<NewsComment> comments) {
        this.comments = comments;
    }

    public NewsComment getAddNews() {
        return addNews;
    }

    public void setAddNews(NewsComment addNews) {
        this.addNews = addNews;
    }
    
    public String addNews(){
        Database db = new Database();
        this.addNews.setUser_id(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenUserId")));
        this.addNews.setNews_id(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hiddenNewsId")));
        db.AddNewsComment(this.addNews);
        return "details";
    }
    
    @ManagedProperty("#{param.id}")
    private String id;
    public NewsDetailBean() {
        addNews = new NewsComment();
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
