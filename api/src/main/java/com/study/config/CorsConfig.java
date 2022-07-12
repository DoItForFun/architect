package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author lizhe
 * @version 1.0.0
 * Created by IDEA
 * @date 2022-07-12 23:44
 */
@Configuration
public class CorsConfig {
    public CorsConfig(){

    }
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:8080");
        // 设置是否发送cookie
        configuration.setAllowCredentials(true);
        // 设置请求方式
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        // 为url添加映射
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", configuration);
        // 返回corsSource
        return new CorsFilter(corsConfigurationSource);
    }
}
