package fr.triedge.website.controllers;

import com.opensymphony.xwork2.ActionContext;
import fr.triedge.website.model.User;
import fr.triedge.website.storage.DB;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
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
                    Cookie userCookie = new Cookie("userCookie", String.valueOf(u.getId()));
                    //userCookie.setDomain("triedge.ovh");
                    //userCookie.setSecure(true);
                    userCookie.setMaxAge(60*60*24*30); // 30 days
                    ServletActionContext.getResponse().addCookie(userCookie);//getContext().getServletResponse().addCookie(userCookie);
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
