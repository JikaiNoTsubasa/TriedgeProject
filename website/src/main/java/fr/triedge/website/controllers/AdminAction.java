package fr.triedge.website.controllers;

import com.opensymphony.xwork2.ActionContext;
import fr.triedge.website.model.Article;
import fr.triedge.website.model.User;
import fr.triedge.website.storage.DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminAction extends SecureAction{

    private int articleNumber;
    private ArrayList<Article> articles;

    @Override
    public String executeSecuredAction(String action) {
        String result = "success";
        if (action == null)
            return exeDefaultPage();
        switch (action){
            case "newArticle":
                result = exeNewArticle();
                break;
            default:
                result = exeDefaultPage();
                break;
        }
        return result;
    }

    public String exeNewArticle(){
        return "newArticle";
    }

    public String exeDefaultPage(){
        try {
            articles = DB.getInstance().getArticles();
            articleNumber = articles.size();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
