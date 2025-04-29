package com.fit.util.spring.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fit.util.spring.SessionUtil;

@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {

    private List<String> excludeUrls;

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    /**
     * 完成页面的render后调用
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

    }

    /**
     * 在调用controller具体方法后拦截
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在调用controller具体方法前拦截
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        log.debug("SecurityInterceptor Get Request URL: {}", url);
        if (excludeUrls.contains(url) || url.contains("static")) {
            return true;
        } else {
            //SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(ResourceUtil.getSessionInfoName());
            String isAdmin = (String) SessionUtil.getAttr(request, "isAdmin");
            if (null != isAdmin && isAdmin.equals("0")) {// 超管不需要验证权限
                return true;
            } else {
                List<String> urls = (List<String>) SessionUtil.getAttr(request, "authUrls");
                if (null != urls && urls.contains(url)) {
                    return true;
                } else {
                    request.setAttribute("url", url);
                    request.getRequestDispatcher("/admin/login.html").forward(request, response);
                    return false;
                }
            }
        }
    }
}