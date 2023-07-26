package fr.triedge.amadeus.services;

import fr.triedge.amadeus.model.*;
import fr.triedge.fwk.security.SPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

public class DB {

    Logger log = LoggerFactory.getLogger(DB.class);

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
        SPassword pwd = new SPassword("c2JpLSRiaXVzZXJTIzg4");
        String host = "localhost";
        if (System.getProperty("host") != null){
            host = System.getProperty("host");
        }
        connection = DriverManager.getConnection("jdbc:mysql://"+host+"/amadeus","amadeus",pwd.getDecrypted());
        log.debug("DB connection reset");
    }

    private User createUser(ResultSet res) throws SQLException {
        if (res == null)
            return null;
        User u = new User();
        u.setId(res.getInt("user_id"));
        u.setName(res.getString("user_name"));
        u.setDisplayName(res.getString("user_display_name"));
        u.setLevel(res.getInt("user_level"));
        u.setTheme(res.getString("user_theme"));
        u.setImage(res.getString("user_image"));
        u.setDescription(res.getString("user_desc"));
        return u;
    }

    public User loadUser(String username) throws SQLException{
        String sql = "select * from ama_user where user_name=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1,username);
        ResultSet res = stmt.executeQuery();
        User u = null;
        if (res.next()){
            u = createUser(res);
        }
        res.close();
        stmt.close();
        return u;
    }

    public User login(String username, String password) throws SQLException {
        log.debug("Login user against DB");
        User user = null;
        String sql = "select * from ama_user where user_name=? and user_password=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1,username);
        stmt.setString(2,new SPassword(password).getEncrypted());
        ResultSet res = stmt.executeQuery();
        if (res.next()){
            user = createUser(res);
        }
        res.close();
        stmt.close();
        if (user == null)
            log.warn("No user found for username: "+username);
        else log.debug("User "+username+" found in DB");
        return user;
    }

    public Document getDocument(int id) throws SQLException {
        Document doc = null;
        String sql = "select * from ama_document where doc_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet res = stmt.executeQuery();
        if (res.next()){
            doc = createDocument(res);
        }
        res.close();
        stmt.close();
        return doc;
    }

    private Document createDocument(ResultSet res) throws SQLException {
        Document doc = new Document();
        doc.setId(res.getInt("doc_id"));
        doc.setName(res.getString("doc_name"));
        doc.setPath(res.getString("doc_path"));
        doc.setFolder(getFolder(res.getInt("doc_folder")));
        return doc;
    }

    public Folder getFolder(int id) throws SQLException {
        Folder fo = null;
        String sql = "select * from ama_folder where folder_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet res = stmt.executeQuery();
        if (res.next()){
            fo = createFolder(res);
        }
        res.close();
        stmt.close();
        return fo;
    }

    private Folder createFolder(ResultSet res) throws SQLException {
        Folder fo = new Folder();
        fo.setId(res.getInt("folder_id"));
        fo.setName(res.getString("folder_name"));
        fo.setParentId(res.getInt("folder_parent"));
        return fo;
    }

    public Folder getRootFolder() throws SQLException {
        return getFolder(1);
    }

    public ArrayList<Folder> getSubFodlers(int parentId) throws SQLException {
        ArrayList<Folder> fos = new ArrayList<>();
        String sql = "select * from ama_folder where folder_parent=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, parentId);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            Folder fo = getFolder(res.getInt("folder_id"));
            fos.add(fo);
        }
        res.close();
        stmt.close();
        return fos;
    }

    public ArrayList<Document> getDocumentsInFolder(int parentId) throws SQLException {
        ArrayList<Document> docs = new ArrayList<>();
        String sql = "select * from ama_document where doc_folder=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1, parentId);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            Document doc = getDocument(res.getInt("doc_id"));
            docs.add(doc);
        }
        res.close();
        stmt.close();
        return docs;
    }

    public void insertDocument(String name, String path) throws SQLException {
        String sql = "insert into ama_document(doc_name, doc_path)values(?,?)";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,path);
        stmt.executeUpdate();
        stmt.close();
    }

    public void insertDocument(String name, String path, int folderId) throws SQLException {
        String sql = "insert into ama_document(doc_name, doc_path, doc_folder)values(?,?,?)";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, path);
        stmt.setInt(3, folderId);
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<Project> getAllProjects() throws SQLException {
        ArrayList<Project> projects = new ArrayList<>();
        String sql = "select * from ama_project";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            Project prj = new Project();
            prj.setId(res.getInt("project_id"));
            prj.setName(res.getString("project_name"));
            prj.setDesc(res.getString("project_desc"));
            prj.setTasks(getTaskForProject(prj.getId()));
            projects.add(prj);
        }
        res.close();
        stmt.close();
        return projects;
    }

    public Project getProject(int id) throws SQLException {
        Project prj = null;
        String sql = "select * from ama_project where project_id=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet res = stmt.executeQuery();
        if (res.next()){
            prj = new Project();
            prj.setId(res.getInt("project_id"));
            prj.setName(res.getString("project_name"));
            prj.setDesc(res.getString("project_desc"));
            prj.setTasks(getTaskForProject(prj.getId()));
        }
        res.close();
        stmt.close();
        return prj;
    }

    public ArrayList<Task> getTaskForProject(int projectId) throws SQLException {
        ArrayList<Task> tasks = new ArrayList<>();
        String sql = "select * from ama_task where task_project=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1,projectId);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            Task t = new Task();
            t.setId(res.getInt("task_id"));
            t.setName(res.getString("task_name"));
            t.setDesc(res.getString("task_desc"));
            t.setResources(getResourceForTask(t.getId()));
            tasks.add(t);
        }
        return tasks;
    }

    public ArrayList<Resource> getResourceForTask(int taskId) throws SQLException {
        ArrayList<Resource> resources = new ArrayList<>();
        String sql = "select * from ama_resource where resource_task=?";
        PreparedStatement stmt = getConnection().prepareStatement(sql);
        stmt.setInt(1,taskId);
        ResultSet res = stmt.executeQuery();
        while (res.next()){
            Resource r = new Resource();
            r.setId(res.getInt("resource_id"));
            r.setName(res.getString("resource_name"));
            r.setDesc(res.getString("resource_desc"));
            resources.add(r);
        }
        return resources;
    }
}
