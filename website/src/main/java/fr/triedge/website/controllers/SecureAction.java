package fr.triedge.website.controllers;

import com.opensymphony.xwork2.ActionContext;
import fr.triedge.website.model.User;

public abstract class SecureAction {

    protected static final String SUCCESS="success";
    private User user;
    private String strutsAction;

    public String execute(){
        user = (User) ActionContext.getContext().getSession().get("tuser");
        if (user == null){
            return "login";
        }

        return executeSecuredAction(getStrutsAction());
    }

    public abstract String executeSecuredAction(String action);

    public String getStrutsAction() {
        return strutsAction;
    }

    public void setStrutsAction(String strutsAction) {
        this.strutsAction = strutsAction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
