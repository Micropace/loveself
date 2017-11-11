package com.weibu.loveself.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Restful 请求Filter，设置请求数据格式、跨域支持
 */
public class RequestFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(RequestFilter.class);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setCharacterEncoding("utf8");
        res.setContentType("application/json; charset=utf8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT");
        res.setHeader("Access-Control-Max-Age", "3600");

        //打印请求信息
        logRequest(req);

        //拦截请求
        if(req.getServletPath().equals("/")) {
            res.getWriter().write("Loveself Mini App API Server");
        }
        else
            chain.doFilter(request, response);

        //TODO 自定义Tocken的实现及登录过滤
        //1. 用户名密码登录时生成Tocken，返回给调用者，调用者定期更新Tocken
//        int code = authorize(request);
//        if (code != 200) {
//            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//
//            ObjectMapper mapper = new ObjectMapper();
//            HashMap<String, Object> result = new HashMap<>();
//            result.put("status", code);
//            result.put("code", "Authorization fail");
//            res.getWriter().write(mapper.writeValueAsString(result));
//        }
//        else {
//            chain.doFilter(request, response);
//        }
    }

    private void logRequest(HttpServletRequest request) {
        String req = request.getMethod() + " from " + request.getRemoteHost() +  " path " + request.getServletPath();
        if(request.getMethod().toLowerCase().equals("post")) {
            try {
                req += " param " + mapper.writeValueAsString(request.getParameterMap());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        logger.info(req);
    }

    // TODO 验证接口TOKEN是否有效
    private int authorize(ServletRequest request) {
        try {
            HttpServletRequest req = (HttpServletRequest)request;
            String token = req.getHeader("Access-Token");
            if (token != null) {
                if (token.compareTo("12345") == 0) {
                    return 200;
                }
            }
            return 403;
        }
        catch(Exception ex) {
            return 403;
        }
    }
}