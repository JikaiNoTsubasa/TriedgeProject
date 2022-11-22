package fr.triedge.website.controllers;

import com.opensymphony.xwork2.ActionContext;
import fr.triedge.website.model.User;

public class AdminAction {

    private User user;

    public String execute(){
        user = (User) ActionContext.getContext().getSession().get("tuser");
        if (user == null){
            return "login";
        }
        return "success";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
