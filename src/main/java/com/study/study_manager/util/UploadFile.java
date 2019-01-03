package com.study.study_manager.util;

import com.study.study_manager.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class UploadFile {
    private Logger logger = LoggerFactory.getLogger(UploadFile.class);
    @Value("${img.location}")
    private String location;

    public String uploadImg(MultipartFile uploadImg){
        String contentType = uploadImg.getContentType();
        String fileName = uploadImg.getOriginalFilename();
        logger.info("上传图片:name={},type={}", fileName, contentType);
        String filePath = location; // 上传后的路径
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File newFile = new File(filePath + fileName);
        try {
            uploadImg.transferTo(newFile);//上传文件写到服务器上指定的文件(spring mvc)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
