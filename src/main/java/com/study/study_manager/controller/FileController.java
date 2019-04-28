package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.entity.File;
import com.study.study_manager.service.FileService;
import com.study.study_manager.util.UploadFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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
    @Value("${localfiledesk.location}")
    private String localpath;
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
        if(name.equals("exists")){
            try {
                throw new Exception("文件存在");
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(1,"文件存在");
            }
        }
        File file1 = new File();
        file1.setName(name);
        file1.setPath(path+ java.io.File.separator +name);
        file1.setPcode(code);
        file1.setCode(UUID.randomUUID().toString());
        file1.setIcon("fa fa-file");
        file1.setType(1);
        fileService.insert(file1);
        return OK;
    }

    /***
     * 新增目录
     * @param dirname
     * @return
     */
    @RequestMapping("/addDir")
    @ResponseBody
    public Result addDir(@RequestParam String dirname,@RequestParam String codeDir){
        File file = new File();
        file.setName(dirname);
        file.setCode(UUID.randomUUID().toString());
        file.setPcode(codeDir);
        file.setType(0);
        fileService.insert(file);
        return OK;
    }

    /***
     * 最外层新增目录
     * @param dirname
     * @return
     */
    @RequestMapping("/addFirstDir")
    @ResponseBody
    public Result addFirstDir(@RequestParam String dirname){
        File file = new File();
        file.setName(dirname);
        file.setCode(UUID.randomUUID().toString());
        file.setType(0);
        fileService.insert(file);
        return OK;
    }

    /***
     * 修改
     * @param newName
     * @param updateCode
     * @return
     */
    @RequestMapping("/updateName")
    @ResponseBody
    public Result updateName(@RequestParam String newName,@RequestParam String updateCode){
        fileService.updateNameByCode(newName,updateCode);
        return OK;
    }

    /***
     * 修改
     * @param newName
     * @param updateCode
     * @return
     */
    @RequestMapping("/updateFileName")
    @ResponseBody
    public Result updateFileName(@RequestParam String newName,@RequestParam String updateCode){
        File file = fileService.getFileByCode(updateCode);
        String path = file.getPath();
        java.io.File oldFile = new java.io.File(path);
        if(!oldFile.exists()){
            return new Result(1,"文件不存在");
        }
        String newPath = path.substring(0,path.lastIndexOf("\\"))+"\\"+newName+path.substring(path.lastIndexOf("."));
        java.io.File newFile = new java.io.File(newPath);
        if(newFile.exists()){
            return new Result(1,"文件命名重复，不可修改");
        }
        try {
            oldFile.renameTo(newFile);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("修改文件名异常！");
        }
        fileService.updateNamePathByCode(newPath.substring(newPath.lastIndexOf("\\")+1),newPath,updateCode);
        return OK;
    }

    /***
     * 查看文件
     * @param code
     */
    @RequestMapping("/lookFile")
    @ResponseBody
    public Result lookFile(@RequestParam String code){
        String path = fileService.getFilePath(code);
        java.io.File file = new java.io.File(path);
        if(file.exists()){
            //copy到本地路径
            java.io.File fileDir = new java.io.File(localpath);
            if(!fileDir.exists()){
                fileDir.mkdir();
            }
            String localPath = localpath+path.substring(path.lastIndexOf("\\"));
            java.io.File file1 = new java.io.File(localPath);
            try {
                FileUtils.copyFile(file,file1);
                Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler file://"+localPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return OK;
        }else {
            return new Result(1,"文件不存在");
        }
    }

    /***
     * 同步文件
     * @param name
     * @return
     */
    @RequestMapping("/synchroFile")
    @ResponseBody
    public Result synchroFile(@RequestParam String name){
        String p = localpath+ java.io.File.separator+name;
        java.io.File file = new java.io.File(p);
        if(file.exists()){
            String op = path+ java.io.File.separator+name;
            java.io.File file1 = new java.io.File(op);
            try {
                FileUtils.copyFile(file,file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return OK;
    }

    @RequestMapping("/deletedFile")
    @ResponseBody
    public Result deletedFile(@RequestParam String code,@RequestParam String name){
        Integer type = fileService.getTypeByCode(code);
        if(type==0){
            //文件夹
            List<Integer> idList = fileService.getIdListByCode(code);
            List<String> nameList = fileService.getNameListByCode(code);
            fileService.deleteByIds(StringUtils.join(idList.toArray(),","));
            fileService.delete(code);
            for(String n:nameList){
                fileService.deleteFile(n);
            }
        }else {
            //文件
            //删除文件
            fileService.deleteFile(name);
            fileService.delete(code);
        }
        return OK;
    }

}
