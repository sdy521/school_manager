package com.study.study_manager.controller;
import com.alibaba.fastjson.JSON;
import com.study.study_manager.dto.UeditorParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class UeditorController {
    @Value("${img.location}")
    private String PATH;
    private String ueditorPath = "/ueditor/jsp/";
    @RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(@RequestParam("action") String param, MultipartFile upfile, HttpServletRequest request) {
        UeditorParam ueditor = new UeditorParam();
        if(param!=null&param.equals("config")){
            return initConfig();
        }else if(param!=null&param.equals("uploadimage")||param.equals("uploadscrawl")){
            if(upfile!=null){
                //{state：”数据状态信息”，url：”图片回显路径”，title：”文件title”，original：”文件名称”，···}
                try {
                    return uploadImg(upfile,request);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    ueditor.setState("出现异常");
                    return JSON.toJSONString(ueditor);
                }
            }else{
                ueditor.setState("文件为空");
                return JSON.toJSONString(ueditor);
            }
        }else{
            ueditor.setState("不支持该操作");
            return JSON.toJSONString(ueditor);
        }
    }

    public String uploadImg(MultipartFile file,
                            HttpServletRequest request) throws IOException {
        UeditorParam ueditor = new UeditorParam();
        String path=PATH;
        String ct = file.getContentType() ;
        String fileType = "";
        if (ct.indexOf("/")>0) {
            fileType = ct.substring(ct.indexOf("/")+1);
        }
        String fileName = UUID.randomUUID() + "." + fileType;
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        File targetFile2 = new File(path+"/"+fileName);
        if (!targetFile2.exists()) {
            targetFile2.createNewFile();
        }
        // 保存
        try {
            file.transferTo(targetFile2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ueditor.setState("SUCCESS");
        ueditor.setTitle(fileName);
        ueditor.setOriginal(fileName);
        ueditor.setUrl("/imgPath"+ File.separator+fileName);
        System.out.println( JSON.toJSONString(ueditor));
        return JSON.toJSONString(ueditor);
    }

    private String initConfig() {
        Map<String, Object> config = new HashMap<String, Object>();
        config.put("imageActionName", "uploadimage");
        config.put("imageFieldName", "upfile");
        config.put("imageMaxSize", 2048000);
        config.put("imageAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
        config.put("imageCompressEnable", true);
        config.put("imageCompressBorder", 1600);
        config.put("imageInsertAlign", "none");
        config.put("imageUrlPrefix", "");
        config.put("imagePathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");

        config.put("scrawlActionName", "uploadscrawl");
        config.put("scrawlFieldName", "upfile");
        config.put("scrawlPathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
        config.put("scrawlMaxSize", 2048000);
        config.put("scrawlUrlPrefix", "");
        config.put("scrawlInsertAlign", "none");

        config.put("snapscreenActionName", "uploadimage");
        config.put("snapscreenInsertAlign", "none");
        config.put("snapscreenPathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
        config.put("snapscreenUrlPrefix", "");

        config.put("catcherActionName", "catchimage");
        config.put("catcherAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
        config.put("catcherPathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
        config.put("catcherMaxSize", 2048000);
        config.put("catcherLocalDomain", new String[]{"127.0.0.1", "localhost", "img.baidu.com"});
        config.put("catcherUrlPrefix", "");
        config.put("catcherFieldName", "source");

        config.put("videoMaxSize", 102400000);
        config.put("videoActionName", "uploadvideo");
        config.put("videoFieldName", "upfile");
        config.put("videoUrlPrefix", "");
        config.put("videoPathFormat", ueditorPath + "/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}");
        config.put("videoAllowFiles", new String[]{".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"});

        config.put("fileManagerActionName", "listfile");
        config.put("fileManagerListPath", ueditorPath + "/upload/file/");
        config.put("fileManagerAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid", ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"});
        config.put("fileManagerUrlPrefix", "");
        config.put("fileManagerListSize", 20);

        config.put("imageManagerActionName", "listimage");
        config.put("imageManagerAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
        config.put("imageManagerListPath", ueditorPath + "/upload/image/");
        config.put("imageManagerUrlPrefix", "");
        config.put("imageManagerInsertAlign", "none");
        config.put("imageManagerListSize", 20);

        config.put("fileActionName", "uploadfile");
        config.put("fileMaxSize", 51200000);
        config.put("fileFieldName", "upfile");
        config.put("fileUrlPrefix", "");
        config.put("filePathFormat", ueditorPath + "/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}");
        config.put("fileAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid", ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"});

        return JSON.toJSONString(config);
    }
}
