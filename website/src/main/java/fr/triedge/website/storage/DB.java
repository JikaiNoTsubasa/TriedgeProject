package fr.triedge.website.storage;

import fr.triedge.website.model.Article;
import fr.triedge.website.model.Category;
import fr.triedge.website.model.User;
import fr.triedge.website.utils.PWDManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class DB {

    private static DB instance;
    private Connection connection;

    private DB(){}

    public static DB getInstance(){
        if (instance == null){
            instance = new DB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()){
            resetConnection();
        }
        return connection;
    }

    public void resetConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PWDManager manager = new PWDManager();
        String pwd = manager.decode("JGJpdXNlclMjODg=");
        String host = "localhost";
        if (System.getProperty("host") != null){
            host = System.getProperty("host");
        }
        connection = DriverManager.getConnection("jdbc:mysql://"+host+":3306/amadeus","amadeus",pwd);
    }

    public Article getArticle(int id) throws SQLException {
        Article article = null;
        String sql = "select * from tr_article left join tr_category on article_category=category_id left join ama_user on article_user=user_id where article_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt((int)1,id);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            Article a = new Article();
            a.setId(res.getInt("article_id"));
            a.setTitle(res.getString("article_title"));
            a.setContent(res.getString("article_content"));
            a.setDate(new Date(res.getTimestamp("article_date").getTime()));
            a.setThumbnail(res.getString("article_thumbnail"));
            a.setDescription(res.getString("article_desc"));

            Category c = new Category();
            c.setId(res.getInt("category_id"));
            c.setName(res.getString("category_name"));
            c.setColor(res.getString("category_color"));
            a.setCategory(c);

            User u = new User();
            u.setId(res.getInt("user_id"));
            u.setEmail(res.getString("user_name"));
            u.setName(res.getString("user_display_name"));
            u.setImage(res.getString("user_image"));
            a.setUser(u);

            article = a;
        }
        return article;
    }

    public ArrayList<Article> getArticles() throws SQLException {
        ArrayList<Article> list = new ArrayList<>();
        String sql = "select * from tr_article left join tr_category on article_category=category_id left join ama_user on article_user=user_id";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            Article a = new Article();
            a.setId(res.getInt("article_id"));
            a.setTitle(res.getString("article_title"));
            a.setContent(res.getString("article_content"));
            a.setDate(new Date(res.getTimestamp("article_date").getTime()));
            a.setThumbnail(res.getString("article_thumbnail"));
            a.setDescription(res.getString("article_desc"));

            Category c = new Category();
            c.setId(res.getInt("category_id"));
            c.setName(res.getString("category_name"));
            c.setColor(res.getString("category_color"));
            a.setCategory(c);

            User u = new User();
            u.setId(res.getInt("user_id"));
            u.setEmail(res.getString("user_name"));
            u.setName(res.getString("user_display_name"));
            u.setImage(res.getString("user_image"));
            a.setUser(u);

            list.add(a);
        }
        return list;
    }

    public User getUser(int id) throws SQLException {
        User user = null;
        String sql = "select * from ama_user where user_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt((int)1,id);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            User u = new User();
            u.setId(res.getInt("user_id"));
            u.setEmail(res.getString("user_name"));
            u.setName(res.getString("user_display_name"));
            u.setImage(res.getString("user_image"));
            u.setDescription(res.getString("user_desc"));
            user = u;
        }
        return user;
    }

    public User loginUser(String name, String password, boolean isEncrypted) throws SQLException {
        if (name == null || password == null)
            return null;
        String pwd = password;
        if (isEncrypted)
            pwd = new PWDManager().encode(password);
        String sql = "select * from ama_user where user_name=? and user_password2=?";
        User user = null;
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString((int)1, name);
        stmt.setString((int)2, pwd);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            user = new User();
            user.setId(res.getInt("user_id"));
            user.setName(res.getString("user_display_name"));
            user.setEmail(res.getString("user_display_name"));
            user.setImage(res.getString("user_image"));
            user.setDescription(res.getString("user_desc"));
        }
        res.close();
        stmt.close();

        return user;
    }
}
