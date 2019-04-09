package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.util.UploadFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author sdy
 * @date 2019/4/9 13:24
 */
@Controller
@RequestMapping("/wordConverterPdf")
public class WordConverterTOPdfController extends BaseController{

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("wordConverterPdf"));
        return "/modular/wordConverterPdf/list";
    }

    /***
     * 上传word文件
     * @param file
     * @return
     */
    @RequestMapping("/uploadWord")
    @ResponseBody
    public Result uploadWord(MultipartFile file){
        UploadFile.uploadWord(file);
        return OK;
    }
}
