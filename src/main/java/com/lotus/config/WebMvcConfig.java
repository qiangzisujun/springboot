package com.lotus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by liuzhiqiang on 2018/5/10.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @SuppressWarnings("deprecation")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(errorPageInterceptor);//.addPathPatterns("/action/**", "/mine/**");默认所有
        super.addInterceptors(registry);
    }
}
