package org.vean.platform.web.exceptionhandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.vean.platform.common.common.HttpResult;
import org.vean.platform.common.exception.BusinessException;
import org.vean.platform.common.exception.ErrorEnum;
import org.vean.platform.common.exception.SystemException;

import com.alibaba.fastjson.JSON;

public class DefaultExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof BusinessException) {
            System.out.println("业务处理异常");
            BusinessException businessException = (BusinessException) e;
            putErrorEnumToResponse(businessException.getErrorEnum(), httpServletResponse);
        } else if (e instanceof SystemException) {
            System.out.println("系统异常");
            SystemException systemException = (SystemException) e;
            putErrorEnumToResponse(systemException.getErrorEnum(), httpServletResponse);
        } else {
            System.out.println("未知异常");
            putErrorEnumToResponse(ErrorEnum.UNKNOWN_EXCEPTION, httpServletResponse);
        }
        return null;
    }

    private static void putErrorEnumToResponse(ErrorEnum errorEnum, HttpServletResponse response) {
        String result = JSON.toJSONString(HttpResult.failedResult(errorEnum));
        response.setContentType("application/json;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            if (out != null)
            	out.close();
        } catch (IOException e) {
            System.out.println("put error msg to response exception");
            e.printStackTrace();
        }
    }

}
