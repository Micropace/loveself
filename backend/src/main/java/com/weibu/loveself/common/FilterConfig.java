package com.weibu.loveself.common;

import com.weibu.loveself.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring HTTP请求过滤器注册
 */
@Component
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        //配置Restful请求过滤器
        RequestFilter filter = new RequestFilter();
        registrationBean.setFilter(filter);
        List<String> urlPatterns = new ArrayList<String>();

        //指定需要过滤的路由
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
