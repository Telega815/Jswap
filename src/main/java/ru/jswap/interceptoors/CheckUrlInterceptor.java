package ru.jswap.interceptoors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component(value = "checkUrlInterceptor")
public class CheckUrlInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String url = request.getRequestURL().substring(22);
        if(url.equals("login")|| url!=null || url.equals(""))
            return true;
        return false;
    }
}
