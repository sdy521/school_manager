package com.study.study_manager.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
//配置mybatis mapper的扫描路径
@MapperScan("com.study.study_manager.dao")
public class MybatisConfig {
   @Bean(name="datasource")
   @ConfigurationProperties("spring.datasource")
   public DataSource dataSource1(){
       return DataSourceBuilder.create().build();
   }
}
