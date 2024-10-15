package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@Configuration注解表示这是一个配置类，Spring容器会加载这个类中的所有@Bean定义的Bean。
@Configuration
public class CorsConfig {//跨域请求的配置

    // 当前跨域请求的结果可以被缓存的时间。这里默认1天
    private static final long MAX_AGE = 24 * 60 * 60;

    @Bean//@Bean注解表示该方法返回的Bean将被Spring容器管理
    public CorsFilter corsFilter() {
        //创建了UrlBasedCorsConfigurationSource和CorsConfiguration的实例，用于定义跨域规则。
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("http://localhost:8081"); // 1 设置允许访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头:允许所有的请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法:允许所有的HTTP方法（GET, POST, PUT, DELETE等）。
        corsConfiguration.setMaxAge(MAX_AGE);//设置预检请求的结果可以缓存的最大时间为MAX_AGE。
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置:将跨域配置应用到所有路径（/**表示项目中的所有路径）。
        return new CorsFilter(source);//返回一个实例 该实例使用前面设置的跨域配置
    }
}

