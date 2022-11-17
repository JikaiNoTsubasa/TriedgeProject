package fr.triedge.website.controllers;

import fr.triedge.website.model.Article;
import fr.triedge.website.storage.DB;

import java.sql.SQLException;

public class ArticleAction {

    private Article article;
    private String strutsArticleId;
    private String strutsAction;

    public String execute(){
        if (strutsArticleId!=null){
            //System.out.println("Article id: "+strutsArticleId);
            int id = Integer.parseInt(strutsArticleId);
            try {
                article = DB.getInstance().getArticle(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.err.println("Article id is null");
        }

        return "success";
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getStrutsArticleId() {
        return strutsArticleId;
    }

    public void setStrutsArticleId(String strutsArticleId) {
        this.strutsArticleId = strutsArticleId;
    }

    public String getStrutsAction() {
        return strutsAction;
    }

    public void setStrutsAction(String strutsAction) {
        this.strutsAction = strutsAction;
    }
}
