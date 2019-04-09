package com.study.study_manager.controller;

import com.study.study_manager.core.JSONResult;
import com.study.study_manager.core.Result;
import com.study.study_manager.util.UploadFile;
import com.study.study_manager.util.WordPdfUtils;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sdy
 * @date 2019/4/9 13:24
 */
@Controller
@RequestMapping("/wordConverterPdf")
public class WordConverterTOPdfController extends BaseController{

    @Value("${word.location}")
    private String localPath;
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
    public Result uploadWord(MultipartFile file) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
        String wordName = UploadFile.uploadWord(file);
        String name = wordName.substring(0,wordName.lastIndexOf("."));
        String target = localPath+File.separator+wordName;
        InputStream targetPath = new FileInputStream(target);
        File file1 = new File(localPath+File.separator+"pdf");
        if(!file1.exists()){
            file1.mkdir();
        }
        String outPath = localPath+File.separator+"pdf"+File.separator+name+".pdf";
        OutputStream outDir = new FileOutputStream(outPath);

        PdfOptions pdfOptions = PdfOptions.create();
        Map<String,String> params = new HashMap<>();
        WordPdfUtils.wordConverterToPdf(targetPath,outDir,pdfOptions,params);
        return OK;
    }
}
