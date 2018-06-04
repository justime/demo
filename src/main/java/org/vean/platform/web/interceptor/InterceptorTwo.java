package org.vean.platform.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorTwo implements AbstractInterceptor {
    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response) {
        //在这里写验证逻辑，验证通过返回true，验证失败返回false
        System.out.println("pass interceptorTwo");
        return true;
    }
}
