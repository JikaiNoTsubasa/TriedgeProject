package fr.triedge.website.controllers.ajax;

import fr.triedge.website.storage.DB;

import java.sql.SQLException;

public class AjaxManageTemp extends AjaxAbstractAction{

    private String strutsUserId;
    private String strutsKey;
    private String strutsContent;

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
        if (strutsUserId==null || strutsKey==null || strutsContent==null)
            return "";
        int id = Integer.parseInt(strutsUserId);
        try {
            DB.getInstance().saveTemp(id, strutsKey, strutsContent);
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

    public String getStrutsKey() {
        return strutsKey;
    }

    public void setStrutsKey(String strutsKey) {
        this.strutsKey = strutsKey;
    }

    public String getStrutsContent() {
        return strutsContent;
    }

    public void setStrutsContent(String strutsContent) {
        this.strutsContent = strutsContent;
    }
}
