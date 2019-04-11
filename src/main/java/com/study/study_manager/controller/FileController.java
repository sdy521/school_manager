package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.entity.File;
import com.study.study_manager.service.FileService;
import com.study.study_manager.util.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 文件管理中心
 * @author sdy
 * @date 2019/4/11 10:48
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController{

    @Value("${filedesk.location}")
    private String path;
    @Resource
    private FileService fileService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("file"));
        return "/modular/file/list";
    }

    /***
     * 建树
     * @return
     */
    @RequestMapping("/jsTree")
    @ResponseBody
    public Result jsTree(){
        List<File> fileList = fileService.selectAll();
        return new JSONResult(fileService.getJson(fileList));
    }

    /***
     * 上传文件
     * @param file
     */
    @RequestMapping("/uploadFileDesk")
    @ResponseBody
    public Result uploadFileDesk(MultipartFile file,String code){
        String name = UploadFile.uploadFileDesk(file);
        File file1 = new File();
        file1.setName(name);
        file1.setPath(path+ java.io.File.separator +name);
        file1.setPcode(code);
        file1.setCode(UUID.randomUUID().toString());
        fileService.insert(file1);
        return OK;
    }
}
