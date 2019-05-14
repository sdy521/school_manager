package com.study.school_manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Configuration
public class WebMvcConfig extends  WebMvcConfigurerAdapter {
    @Value("${img.location}")
    private String location;
    /***
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        factory.setMaxFileSize("5MB");//单个文件最大
        factory.setMaxRequestSize("5MB");// 设置总上传数据总大小
        return factory.createMultipartConfig();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *  对文件的路径进行配置,创建一个虚拟路径/Path/** ，即只要在<img src="/imgPath/picName.jpg" />便可以直接引用图片
         *这是图片的物理路径  "file:/+本地图片的地址"
         */
        registry.addResourceHandler("/imgPath/**").addResourceLocations("file:"+location+ File.separator);
        super.addResourceHandlers(registry);
    }

}
