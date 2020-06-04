package com.arh.springbootdemo.web.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description
 * @Author chenli
 * @Date 2019/10/25
 **/

@Configuration
public class WebConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

    @Bean
    public FilterRegistrationBean generateFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        ShowUrlFilter showUrlFilter = new ShowUrlFilter();
        filterRegistrationBean.setFilter(showUrlFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("ShowUrlFilter");
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addInitParameter("name", "ShowUrlFilterName");
        return filterRegistrationBean;
    }

    public class ShowUrlFilter implements Filter {

        private String name;

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            name = filterConfig.getInitParameter("name");
            logger.info("ShowUrlFilter的name：" + name);
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String uri = httpServletRequest.getRequestURI();
            logger.info("start url:" + uri);
            filterChain.doFilter(servletRequest, servletResponse);
            logger.info("end url:" + uri);
        }

        @Override
        public void destroy() {

        }
    }
}
