package fr.triedge.website.controllers;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;

public class DisconnectAction {

    public String execute(){
        ActionContext.getContext().getSession().put("tuser", null);
        for (Cookie cookie : ServletActionContext.getRequest().getCookies()) {
            if (cookie.getName().equals("userCookie")) {
                cookie.setMaxAge(0);
                ServletActionContext.getResponse().addCookie(cookie);
            }
        }
        return "success";
    }
}
