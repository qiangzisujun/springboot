package com.lotus.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuzhiqiang on 2018/5/10.
 */
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {

    private List<Integer> errorCodeList= Arrays.asList(404,403,500,501);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/error/"+response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
