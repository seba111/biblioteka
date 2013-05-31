/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sebastian
 */
@ManagedBean(name = "ReadDataBean")
@ApplicationScoped
public class ReadDataBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<News> newses;
    private ArrayList<News> events;
    private Database db;
    public ReadDataBean(){
        this.newses = new ArrayList<News>();
        this.db = new Database();
    }
    public String test(){
      
        return "register";
    }

    public ArrayList<News> getNewses() {
        
        this.newses= db.GetNewses("NEWS");
        return this.newses;
    }
    
    public ArrayList<News> getEvents() {
        
        this.events= db.GetNewses("EVENT");
        return this.events;
    }

    public void setNewses(ArrayList<News> newses) {
        this.newses = newses;
    }
    
    public void setEvents(ArrayList<News> events) {
        this.events = events;
    }
}
