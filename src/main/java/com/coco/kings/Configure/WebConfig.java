package com.coco.kings.Configure;

import com.coco.kings.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 康森
 * @date 2020/4/1 11 : 25 : 34
 * @description 登录拦截
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/kings/**")
                .excludePathPatterns("/kings")
                .excludePathPatterns("/kings/login");
    }
}
