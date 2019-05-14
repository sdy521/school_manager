package com.study.school_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.school_manager.core.JSONResult;
import com.study.school_manager.core.Result;
import com.study.school_manager.core.jqGrid.JqGridResult;
import com.study.school_manager.dto.TeacherParam;
import com.study.school_manager.entity.User;
import com.study.school_manager.service.InfoService;
import com.study.school_manager.service.NameListService;
import com.study.school_manager.core.system.Constans;
import com.study.school_manager.util.UploadFile;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Controller
@RequestMapping("/teacher_nameList")
public class NameListController extends BaseController{
    @Resource
    private NameListService nameListService;

    @Resource
    private InfoService infoService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("teacher_nameList"));
        return "/modular/nameList/list";
    }

    @RequestMapping("/grid")
    @ResponseBody
    public Result grid(TeacherParam param){
        PageInfo<User> pageInfo = nameListService.selectByPage(param);
        JqGridResult result = new JqGridResult();
        result.setPage(pageInfo.getPageNum());
        result.setRecords(pageInfo.getTotal());
        result.setTotal(pageInfo.getPages());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    /***
     * 增加
     * @param teacher
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(@RequestBody User teacher){
        String pwd = teacher.getPassword();
        String bcrypt = BCrypt.hashpw(pwd,BCrypt.gensalt());
        teacher.setPassword(bcrypt);
        teacher.setType(1);
        nameListService.insert(teacher);
        return OK;
    }

    /***
     * 根据id查找老师
     * @param id
     * @return
     */
    @RequestMapping("/selectOne")
    @ResponseBody
    public Result selectOne(@RequestParam Integer id){
        User teacher = new User();
        teacher.setId(id);
        teacher = nameListService.selectOne(teacher);
        return new JSONResult(teacher);
    }

    /***
     * 修改
     * @param teacher
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@RequestBody User teacher){
        nameListService.updateSelective(teacher);
        return OK;
    }

    /***
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Transactional
    public Result delete(@RequestParam Integer id){
        User teacher = new User();
        teacher.setId(id);
        nameListService.delete(teacher);
        infoService.deleteInfo(id);
        return OK;
    }

    /***
     * 恢复
     * @param id
     * @return
     */
    @RequestMapping("/recover")
    @ResponseBody
    public Result recover(@RequestParam Integer id){
        User teacher = new User();
        teacher.setId(id);
        nameListService.recover(teacher);
        return OK;
    }

    /***
     * 恢复
     * @param id
     * @return
     */
    @RequestMapping("/initpassword")
    @ResponseBody
    public Result initpassword(@RequestParam Integer id){
        User teacher = new User();
        teacher.setId(id);
        teacher.setPassword(BCrypt.hashpw(Constans.DEFAULT_PASSWORD,BCrypt.gensalt()));
        nameListService.updateSelective(teacher);
        return OK;
    }

    /***
     * 批量导入
     * @param file
     * @param enable
     * @return
     */
    @RequestMapping(value = "/importExcel")
    @ResponseBody
    public Result importExcel(MultipartFile file,boolean enable){
        String filename = UploadFile.uploadFile(file);
        boolean result = nameListService.importExcel(filename,enable);
        return OK;
    }

    @RequestMapping(value = "/downloadTemplate")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response, HttpServletRequest request) {
        try {
            //获取文件的路径
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            String excelPath = path+"static/excel/姓名导入模板.xls";
            // 读到流中
            InputStream inStream = new FileInputStream(excelPath);//文件的存放路径
            // 设置输出的格式
            response.reset();
            response.setContentType("bin");//设置文件类型的，bin这个文件类型是不存在的。。浏览器遇到不存在的文件类型就会出现一个下载提示
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode("姓名导入模板.xls", "UTF-8"));
            // 循环取出流中的数据
            byte[] b = new byte[200];
            int len;

            while ((len = inStream.read(b)) > 0){
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
