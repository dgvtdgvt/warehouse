package com.it.config;

import com.it.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configurable
@ComponentScan("com.it.controller")
@PropertySource("classpath:ignoreUrl.properties")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {

    @Value("#{'${ignoreUrl}'.split(',')}")
    private List<String> ignoreUrl;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/pages/",".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResourceInterceptor(ignoreUrl))
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/img/**","/js/**");
    }
}
