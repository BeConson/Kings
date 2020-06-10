package com.coco.kings.Interceptor;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 康森
 * @date 2020/4/1 11 : 19 : 12
 * @description 登录拦截
 */

public class LoginInterceptor extends HandlerInterceptorAdapter implements WebMvcConfigurer {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null){
            response.sendRedirect("/kings");
            return false;
        }
        return true;
    }
}
