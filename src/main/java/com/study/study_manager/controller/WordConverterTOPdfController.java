package com.study.study_manager.controller;

import com.study.study_manager.core.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
