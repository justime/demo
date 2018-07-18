package org.vean.platform.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AbstractInterceptor {
    public boolean check(HttpServletRequest request, HttpServletResponse response);
}
