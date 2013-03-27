/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.DatabaseMetaData;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author sebastian
 */
public class Database {
    private Connection conn = null;
    private Statement stmt = null;

    public Database() {
        try
	{
            Class.forName("org.sqlite.JDBC");
	}
	catch(ClassNotFoundException ex)
	{
            System.err.println(ex.getMessage());
	}
        
        try
	{
            conn = java.sql.DriverManager.getConnection("jdbc:sqlite:base.db");	
            conn.setAutoCommit(true);
            if (conn != null) System.out.println("Polaczono z baza danych sqlite");
            
            
            stmt = conn.createStatement();
            //stmt.executeUpdate("drop table user");
            stmt.executeUpdate("create table if not exists user(userId integer primary key autoincrement, firstname varchar(50), lastname varchar(100), email varchar(30), login varchar(30), password varchar(30), userGroup varchar(30))");
            //stmt.executeUpdate("insert into user(firstname,lastname,email,login,password,userGroup) values('Sebastian','Koch','seba.k@vp.pl','seba','seba','admin')");
            stmt.close();
            System.out.println("Database constructor");
            
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
	}
    }
    public User CheckLoginData(String lg, String ps)
    {
        User usr = null;
        try
	{
            stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("select * from user where login = ? and password = ?");
            pst.setString(1, lg);
            pst.setString(2, ps);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                usr = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                rs.close();
                pst.close();
                stmt.close();
                return usr;  
            }
            rs.close();
            pst.close();
            stmt.close();
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
	}
        return usr;
    }
    public void AddUser(User usr)
    {
        try
	{

            stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("insert into user(firstname,lastname,email,login,password,userGroup) values(?,?,?,?,?,?)");
            pst.setString(1, usr.getFirstName());
            pst.setString(2, usr.getLastName());
            pst.setString(3, usr.getEmail());
            pst.setString(4, usr.getLogin());
            pst.setString(5, usr.getPassword());
            pst.setString(6, "user");
            pst.executeUpdate();
            pst.close(); 
            stmt.close(); 
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
	}       
    }
    public void Close()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
}
