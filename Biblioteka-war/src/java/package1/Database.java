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
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author sebastian
 */
@ManagedBean
@NoneScoped
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
           // stmt.executeUpdate("drop table user");
            // tabela user jeszcze stara wersja !
            stmt.executeUpdate("create table if not exists user(id integer primary key autoincrement, email varchar(100), first_name varchar(50), last_name varchar(50), created_at TEXT, login varchar(32), password varchar(32), avatar varchar(36), status integer)");
            stmt.executeUpdate("create table if not exists News(id integer primary key autoincrement, image varchar(36), title varchar(255), content TEXT, created_at TEXT, user_id integer, category varchar(100), FOREIGN KEY(user_id) REFERENCES User(id))");
            stmt.executeUpdate("create table if not exists News_Comment(id integer primary key autoincrement, news_id integer, user_id integer, created_at TEXT, content TEXT, FOREIGN KEY(news_id) REFERENCES News(id), FOREIGN KEY(user_id) REFERENCES User(id))");
            stmt.executeUpdate("create table if not exists Book(id integer primary key autoincrement, image varchar(36), title varchar(255), desctiption TEXT, yesr TEXT autor varchar(255))");
            stmt.executeUpdate("create table if not exists Book_comment(id integer primary key autoincrement, book_id integer, user_id integer, created_at TEXT, content TEXT, FOREIGN KEY(book_id) REFERENCES Book(id), FOREIGN KEY(user_id) REFERENCES User(id))");
            stmt.executeUpdate("create table if not exists Book_grade(id integer primary key autoincrement, book_id integer, user_id integer, value integer, created_at TEXT, FOREIGN KEY(book_id) REFERENCES Book(id), FOREIGN KEY(user_id) REFERENCES User(id))");
            stmt.executeUpdate("create table if not exists Book_status(id integer primary key autoincrement, book_id integer, user_id integer, rent_time TEXT, back_time TEXT, FOREIGN KEY(book_id) REFERENCES Book(id), FOREIGN KEY(user_id) REFERENCES User(id))");
            stmt.executeUpdate("create table if not exists Book_reservation(id integer primary key autoincrement, book_id integer, user_id integer, created_at TEXT, active integer, FOREIGN KEY(book_id) REFERENCES Book(id), FOREIGN KEY(user_id) REFERENCES User(id))");
            
           // stmt.executeUpdate("insert into user(email,first_name,last_name,created_at,login,password,avatar,status) values('seba.k@vp.pl','Sebastian','Koch',strftime('%Y-%m-%d %H:%M:%S','now'),'seba','seba','brak',1)");
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
                usr = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getInt(9));
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
            PreparedStatement pst = conn.prepareStatement("insert into user(email,first_name,last_name,created_at,login,password,status) values(?,?,?,strftime('%Y-%m-%d %H:%M:%S','now'),?,?,?)");
            pst.setString(1, usr.getEmail());
            pst.setString(2, usr.getFirstName());
            pst.setString(3, usr.getLastName());
            
            pst.setString(4, usr.getLogin());
            pst.setString(5, usr.getPassword());
            pst.setInt(6, 1);
            pst.executeUpdate();
            pst.close(); 
            stmt.close(); 
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
	}       
    }
    public void AddNews(User usr, News nw)
    {
        try
	{

            stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("insert into News(title,content,created_at,user_id,category) values(?,?,strftime('%Y-%m-%d %H:%M:%S','now'),?,?)");
            pst.setString(1, nw.getTitle());
            pst.setString(2, nw.getContent());
            pst.setInt(3, usr.getId());
            pst.setString(4, nw.getCategory());
            pst.executeUpdate();
            pst.close(); 
            stmt.close(); 
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
	}       
    }
    
    public void AddNewsComment(NewsComment comment){
        try
	{

            stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("insert into News_Comment(news_id,user_id,created_at,content) values(?,?,strftime('%Y-%m-%d %H:%M:%S','now'),?)");
            pst.setInt(1, comment.getNews_id());
            pst.setInt(2, comment.getUser_id());
            pst.setString(3, comment.getContent());
            
            pst.executeUpdate();
            pst.close(); 
            stmt.close(); 
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
	}   
    }
    
    
    public ArrayList<News> GetNewses(String type)
    {
        ArrayList<News> lista= new ArrayList<News>();
        
        try
	{
            stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("select * from News WHERE CATEGORY LIKE '" + type  +"'");
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {   
                
                lista.add(new News(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            rs.close();
            pst.close();
            stmt.close();
            
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
            
	}
        return lista;
    }
    
    
    public News getNews(String id){
        News object = null;
        try
	{
            stmt = conn.createStatement();
            PreparedStatement pst = conn.prepareStatement("select * from News WHERE ID = " + id  +"");
            ResultSet rs = pst.executeQuery();
            
            if(rs.next())
            {   
                
                object = new News(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
            }
            rs.close();
            pst.close();
            stmt.close();
            
	}
	catch(SQLException e)
	{
            System.err.println(e.getMessage());
            
	}
        return object;
    }
    public void Close()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
}
