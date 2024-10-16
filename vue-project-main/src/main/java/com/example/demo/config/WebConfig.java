package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//告诉 Spring 容器这个类是一个配置类，Spring 将会扫描并处理其中定义的 bean 和配置
public class WebConfig implements WebMvcConfigurer {//实现 WebMvcConfigurer 接口 意味着 WebConfig 类将能够覆盖接口中的方法来定制 MVC 行为
    @Override//覆盖 WebMvcConfigurer 接口中的 addResourceHandlers 方法 这个方法允许注册资源处理器
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")//传入一个路径模式。在这个例子中，任何以 /images/ 开始的请求都会被匹配
                .addResourceLocations("classpath:/static/images/")//指定静态资源的位置。这里指定的是类路径（classpath）下的 /static/images/ 目录
                .setCachePeriod(0);// 设置缓存时间为0，表示不缓存 .这是告诉 Spring 不要缓存静态资源，每次请求都会重新加载资源

    }
}
