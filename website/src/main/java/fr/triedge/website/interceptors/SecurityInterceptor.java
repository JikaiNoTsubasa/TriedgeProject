package fr.triedge.website.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation inv) throws Exception {
        return inv.invoke();
    }
}
