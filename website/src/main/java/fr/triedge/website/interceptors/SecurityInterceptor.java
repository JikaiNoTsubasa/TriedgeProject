package fr.triedge.website.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import fr.triedge.website.model.User;
import fr.triedge.website.storage.DB;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

// http://sandeepbhardwaj.github.io/2010/12/01/struts2-with-login-interceptor.html
public class SecurityInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation inv) throws Exception {
        String action = inv.getInvocationContext().getActionName();
        //System.out.println("Action: "+action);
        if (action.equalsIgnoreCase("admin")){
            User user = (User) inv.getInvocationContext().getSession().get("tuser");
            if (user == null){
                for (Cookie cookie : inv.getInvocationContext().getServletRequest().getCookies()){
                    if (cookie.getName().equals("userCookie")){
                        try {
                            int id = Integer.parseInt(cookie.getValue());
                            User u = DB.getInstance().getUser(id);
                            if (u != null){
                                inv.getInvocationContext().getSession().put("tuser",u);
                                return inv.invoke();
                            }
                        }catch (Exception e){
                            System.out.println("User cookie id not in INT format");
                        }
                    }
                }
                return "login";
            }
        }
        return inv.invoke();
    }
}
