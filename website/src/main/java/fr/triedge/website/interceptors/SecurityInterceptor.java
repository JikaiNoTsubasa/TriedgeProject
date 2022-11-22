package fr.triedge.website.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import fr.triedge.website.model.User;

// http://sandeepbhardwaj.github.io/2010/12/01/struts2-with-login-interceptor.html
public class SecurityInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation inv) throws Exception {
        String action = inv.getInvocationContext().getActionName();
        System.out.println("Action: "+action);
        if (action.equalsIgnoreCase("admin")){
            User user = (User) inv.getInvocationContext().getSession().get("tuser");
            if (user == null){
                return "login";
            }
        }
        return inv.invoke();
    }
}
