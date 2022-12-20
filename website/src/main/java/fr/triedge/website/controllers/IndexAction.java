package fr.triedge.website.controllers;

import fr.triedge.website.model.Article;
import fr.triedge.website.model.User;
import fr.triedge.website.storage.DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class IndexAction {

    private ArrayList<Article> articles;
    private User user;

    public String execute(){
        try {
            articles = DB.getInstance().getArticles(false);
            user = DB.getInstance().getUser(1);
            //System.out.println("Articles: "+articles.size());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "success";
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
