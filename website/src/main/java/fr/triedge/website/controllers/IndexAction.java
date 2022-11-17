package fr.triedge.website.controllers;

import fr.triedge.website.model.Article;
import fr.triedge.website.storage.DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class IndexAction {

    private ArrayList<Article> articles;

    public String execute(){
        try {
            articles = DB.getInstance().getArticles();
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
}
