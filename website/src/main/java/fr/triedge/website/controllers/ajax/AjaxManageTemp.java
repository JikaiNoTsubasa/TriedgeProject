package fr.triedge.website.controllers.ajax;

import fr.triedge.website.model.Article;
import fr.triedge.website.model.User;
import fr.triedge.website.storage.DB;

import java.sql.SQLException;

public class AjaxManageTemp extends AjaxAbstractAction{

    private String strutsUserId;
    private String strutsArticleTitle;
    private String strutsArticleContent;
    private String strutsArticleDesc;
    private String strutsArticleThumbnail;
    private String strutsArticleId;
    private String strutsArticleCategory;
    private String strutsPublish;

    @Override
    protected String executeAction(String action) {
        if (action == null)
            return "";
        String result = "";
        switch (action){
            case "save":
                result = save();
                break;
        }
        return result;
    }

    private String save(){
        if (strutsUserId==null || strutsArticleTitle==null || strutsArticleContent==null)
            return "";
        int artId = -1;
        if (strutsArticleId != null && strutsArticleId.length()>0){
            artId=Integer.parseInt(strutsArticleId);
        }
        int id = Integer.parseInt(strutsUserId);
        try {
            Article art = new Article();
            art.setId(artId);
            art.setTitle(getStrutsArticleTitle());
            art.setContent(getStrutsArticleContent());
            art.setThumbnail(getStrutsArticleThumbnail());
            art.setDescription(getStrutsArticleDesc());
            art.setPublished((getStrutsPublish()!= null && getStrutsPublish().equals("true"))?true:false);
            if (getStrutsArticleCategory() == null){
                setStrutsArticleCategory("1");
            }
            int catId = Integer.parseInt(getStrutsArticleCategory());
            art.setCategory(DB.getInstance().getCategory(catId));
            User u = DB.getInstance().getUser(id);
            art.setUser(u);
            DB.getInstance().createUpdateArticle(art);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "";
    }

    public String getStrutsUserId() {
        return strutsUserId;
    }

    public void setStrutsUserId(String strutsUserId) {
        this.strutsUserId = strutsUserId;
    }

    public String getStrutsArticleTitle() {
        return strutsArticleTitle;
    }

    public void setStrutsArticleTitle(String strutsArticleTitle) {
        this.strutsArticleTitle = strutsArticleTitle;
    }

    public String getStrutsArticleContent() {
        return strutsArticleContent;
    }

    public void setStrutsArticleContent(String strutsArticleContent) {
        this.strutsArticleContent = strutsArticleContent;
    }

    public String getStrutsArticleId() {
        return strutsArticleId;
    }

    public void setStrutsArticleId(String strutsArticleId) {
        this.strutsArticleId = strutsArticleId;
    }

    public String getStrutsPublish() {
        return strutsPublish;
    }

    public void setStrutsPublish(String strutsPublish) {
        this.strutsPublish = strutsPublish;
    }

    public String getStrutsArticleDesc() {
        return strutsArticleDesc;
    }

    public void setStrutsArticleDesc(String strutsArticleDesc) {
        this.strutsArticleDesc = strutsArticleDesc;
    }

    public String getStrutsArticleThumbnail() {
        return strutsArticleThumbnail;
    }

    public void setStrutsArticleThumbnail(String strutsArticleThumbnail) {
        this.strutsArticleThumbnail = strutsArticleThumbnail;
    }

    public String getStrutsArticleCategory() {
        return strutsArticleCategory;
    }

    public void setStrutsArticleCategory(String strutsArticleCategory) {
        this.strutsArticleCategory = strutsArticleCategory;
    }
}
