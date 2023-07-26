package fr.triedge.amadeus.api;


import fr.triedge.fwk.security.SPassword;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractController {

    public HttpSession getSession(){
        return getHttpReq().getSession(true); // true == allow create
    }

    public HttpServletRequest getHttpReq(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    public HttpServletResponse getHttpRep(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }

    public void createLoginCookie(String name, String value){
        SPassword pwd = new SPassword(value);
        Cookie cookie = new Cookie(name, pwd.getEncrypted());
        cookie.setMaxAge(86400);
        getHttpRep().addCookie(cookie);
    }

    public void deleteLoginCookie(String name){
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        getHttpRep().addCookie(cookie);
    }

}
