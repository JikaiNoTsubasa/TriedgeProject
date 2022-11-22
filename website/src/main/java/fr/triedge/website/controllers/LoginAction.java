package fr.triedge.website.controllers;

import com.opensymphony.xwork2.ActionContext;
import fr.triedge.website.model.User;
import fr.triedge.website.storage.DB;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;

public class LoginAction {

    private String strutsUserName;
    private String strutsPassword;

    public String execute(){
        if (StringUtils.isNotEmpty(strutsUserName) && StringUtils.isNotEmpty(strutsPassword)){
            try {
                User u = DB.getInstance().loginUser(strutsUserName, strutsPassword, true);
                if (u != null){
                    ActionContext.getContext().getSession().put("tuser",u);
                    return "success";
                }else{
                    return "login";
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return "login";
    }

    public String getStrutsUserName() {
        return strutsUserName;
    }

    public void setStrutsUserName(String strutsUserName) {
        this.strutsUserName = strutsUserName;
    }

    public String getStrutsPassword() {
        return strutsPassword;
    }

    public void setStrutsPassword(String strutsPassword) {
        this.strutsPassword = strutsPassword;
    }
}
