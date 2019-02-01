package com.study.study_manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ueditor/")
public class UEditorController {
	
	private Map<String, Object> config;
	private String ueditorPath = "/temp/ueditor/";
	private String videoActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	private String imageActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	private String fileActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	private String scrawlActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	private String snapscreenActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	private String catcherActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	private String fileManagerActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	private String imageManagerActionName = "JS_DEFINE"; //前端自定义处理地址，与前端JS代码对应
	
	
	@RequestMapping(value = "/config")
	@ResponseBody
    public Object config(HttpServletRequest request, HttpServletResponse response,String action) {
		if( null == config){
			initConfig();
		}
		return config;
    }  
	

    private void initConfig() {
		config = new HashMap<String, Object>();
		config.put("videoMaxSize", 102400000);
		config.put("imageActionName", imageActionName);
		config.put("videoActionName", videoActionName);
		config.put("fileActionName", fileActionName);
		config.put("scrawlActionName", scrawlActionName);
		config.put("snapscreenActionName", snapscreenActionName);
		config.put("catcherActionName", catcherActionName);
		config.put("fileManagerActionName", fileManagerActionName);
		config.put("imageManagerActionName", imageManagerActionName);
		config.put("fileManagerListPath", ueditorPath + "/upload/file/");
		config.put("imageCompressBorder", 1600);
		config.put("imageManagerAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
		config.put("imageManagerListPath", ueditorPath + "/upload/image/");
		config.put("fileMaxSize", 51200000);
		config.put("fileManagerAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid", ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"});
		config.put("snapscreenInsertAlign", "none");
		config.put("videoFieldName", "upfile");
		config.put("imageCompressEnable", true);
		config.put("videoUrlPrefix", "");
		config.put("fileManagerUrlPrefix", "");
		config.put("catcherAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
		config.put("snapscreenPathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
		config.put("scrawlPathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
		config.put("scrawlMaxSize", 2048000);
		config.put("imageInsertAlign", "none");
		config.put("catcherPathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
		config.put("catcherMaxSize", 2048000);
		config.put("snapscreenUrlPrefix", "");
		config.put("imagePathFormat", ueditorPath + "/upload/image/{yyyy}{mm}{dd}/{time}{rand:6}");
		config.put("imageManagerUrlPrefix", "");
		config.put("scrawlUrlPrefix", "");
		config.put("scrawlFieldName", "upfile");
		config.put("imageMaxSize", 2048000);
		config.put("imageAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp"});
		config.put("fileFieldName", "upfile");
		config.put("fileUrlPrefix", "");
		config.put("imageManagerInsertAlign", "none");
		config.put("catcherLocalDomain", new String[]{"127.0.0.1", "localhost", "img.baidu.com"});
		config.put("filePathFormat", ueditorPath + "/upload/file/{yyyy}{mm}{dd}/{time}{rand:6}");
		config.put("videoPathFormat", ueditorPath + "/upload/video/{yyyy}{mm}{dd}/{time}{rand:6}");
		config.put("fileManagerListSize", 20);
		config.put("imageFieldName", "upfile");
		config.put("imageUrlPrefix", "");
		config.put("scrawlInsertAlign", "none");
		config.put("fileAllowFiles", new String[]{".png", ".jpg", ".jpeg", ".gif", ".bmp", ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid", ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso", ".doc", ".docx", ".xls", ".xlsx", ".ppt", ".pptx", ".pdf", ".txt", ".md", ".xml"});
		config.put("catcherUrlPrefix", "");
		config.put("imageManagerListSize", 20);
		config.put("catcherFieldName", "source");
		config.put("videoAllowFiles", new String[]{".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg", ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid"});
	}
}
