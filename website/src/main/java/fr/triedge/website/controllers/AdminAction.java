package fr.triedge.website.controllers;

import com.opensymphony.xwork2.ActionContext;
import fr.triedge.website.model.Article;
import fr.triedge.website.model.Draft;
import fr.triedge.website.model.User;
import fr.triedge.website.storage.DB;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminAction extends SecureAction{

    private int articleNumber;
    private int draftNumber;
    private ArrayList<Article> articles;
    private ArrayList<Article> drafts;
    private Article currentDraft;
    private String strutsArticleId;

    @Override
    public String executeSecuredAction(String action) {
        String result = SUCCESS;
        if (action == null)
            return exeDefaultPage();
        switch (action){
            case "newArticle":
                result = exeNewArticle();
                break;
            case "editArticle":
                result = exeEditArticle();
                break;
            case "deleteArticle":
                result = exeDeleteArticle();
                break;
            default:
                result = exeDefaultPage();
                break;
        }
        return result;
    }

    private String exeDeleteArticle(){
        if (getStrutsArticleId() != null && getStrutsArticleId().length()>0){
            int id = Integer.parseInt(getStrutsArticleId());
            try {
                DB.getInstance().deleteArticle(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return exeDefaultPage();
    }

    private String exeNewArticle(){
        return "newArticle";
    }

    private String exeEditArticle(){
        if (getStrutsArticleId() != null && getStrutsArticleId().length()>0){
            int draftId = Integer.parseInt(getStrutsArticleId());
            try {
                currentDraft = DB.getInstance().getArticle(draftId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "newArticle";
    }

    public String exeDefaultPage(){
        try {
            articles = DB.getInstance().getArticles(false);
            articleNumber = articles.size();
            drafts = DB.getInstance().getArticles(true);
            draftNumber = drafts.size();
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

    public int getDraftNumber() {
        return draftNumber;
    }

    public void setDraftNumber(int draftNumber) {
        this.draftNumber = draftNumber;
    }

    public String getStrutsArticleId() {
        return strutsArticleId;
    }

    public void setStrutsArticleId(String strutsArticleId) {
        this.strutsArticleId = strutsArticleId;
    }

    public ArrayList<Article> getDrafts() {
        return drafts;
    }

    public void setDrafts(ArrayList<Article> drafts) {
        this.drafts = drafts;
    }

    public Article getCurrentDraft() {
        return currentDraft;
    }

    public void setCurrentDraft(Article currentDraft) {
        this.currentDraft = currentDraft;
    }
}
