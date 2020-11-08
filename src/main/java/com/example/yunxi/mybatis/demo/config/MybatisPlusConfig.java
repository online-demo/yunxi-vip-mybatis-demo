package com.example.yunxi.mybatis.demo.config;

import com.example.yunxi.mybatis.demo.interceptor.MySqlPagingPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : zhouguanya
 * @Project : java-interview-guide-part2
 * @Date : 2020-11-08 23:18
 * @Version : V1.0
 * @Description : 配置类
 */
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MySqlPagingPlugin paginationInterceptor() {
        MySqlPagingPlugin paginationInterceptor = new MySqlPagingPlugin();
        return paginationInterceptor;
    }

}
