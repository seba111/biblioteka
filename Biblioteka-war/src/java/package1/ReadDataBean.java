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
@SessionScoped
public class ReadDataBean implements Serializable {

    private ArrayList<News> newses = new ArrayList<News>();
    public ReadDataBean(){}
    public String test(){
      
        return "register";
    }

    public ArrayList<News> getNewses() {
        return newses;
    }

    public void setNewses(ArrayList<News> newses) {
        this.newses = newses;
    }

    public Collection<News> GetNewses(){      
        Database db = new Database();
        this.newses= db.GetNewses();
        return this.newses;
    }

}
