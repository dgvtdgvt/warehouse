package com.it.interceptor;

import com.it.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ResourceInterceptor implements HandlerInterceptor {

    private List<String> urls;

    public ResourceInterceptor(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("USER");

        if (requestURI.indexOf("login")>0){
            return true;
        }

        if (user!=null){
            if ("ADMIN".equals(user.getRole())){
                return true;
            }else {
                for (String url:urls){
                    if (requestURI.indexOf(url)>0){
                        return true;
                    }
                }
                request.setAttribute("msg","没有权限，请切换管理员账号");
                request.getRequestDispatcher("/pages/login.jsp").forward(request,response);
                return false;
            }
        }

        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/pages/login.jsp").forward(request,response);

        return false;
    }
}
