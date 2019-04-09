package com.study.study_manager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UploadFile {
    private static Logger logger = LoggerFactory.getLogger(UploadFile.class);
    /***
     * 上传图片
     * @param uploadImg
     * @return
     */
    public static String uploadImg(MultipartFile uploadImg){
        String contentType = uploadImg.getContentType();
        String fileName = uploadImg.getOriginalFilename();
        logger.info("上传图片:name={"+fileName+"},type={"+contentType+"}");
        String location = SpringBeanTool.getApplicationContext().getEnvironment().getProperty("img.location");
        String filePath = location+File.separator; // 上传后的路径
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

    /***
     * 上传文件
     * @param uploadfile
     * @return
     */
    public static String uploadFile(MultipartFile uploadfile){
        String contentType = uploadfile.getContentType();
        String filename = uploadfile.getOriginalFilename();
        logger.info("上传文件:name={"+filename+"},type={"+contentType+"}");
        String location = SpringBeanTool.getApplicationContext().getEnvironment().getProperty("img.location");
        String filePath = location+File.separator; // 上传后的路径
        String suffixName = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID()+suffixName;
        File newFile = new File(filePath+filename);
        try {
            uploadfile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public static String uploadRoute(){
        String location = SpringBeanTool.getApplicationContext().getEnvironment().getProperty("img.location");
        return location+File.separator;
    }

    /***
     * 上传word文件
     * @param uploadfile
     * @return
     */
    public static String uploadWord(MultipartFile uploadfile){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        String contentType = uploadfile.getContentType();
        String filename = uploadfile.getOriginalFilename();
        logger.info("上传文件:name={"+filename+"},type={"+contentType+"}");
        String location = SpringBeanTool.getApplicationContext().getEnvironment().getProperty("word.location");
        String filePath = location+File.separator; // 上传后的路径
        /*String suffixName = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID()+suffixName;*/
        filename =sdf.format(new Date())+"-"+filename;
        File newFile = new File(filePath+filename);
        try {
            uploadfile.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
