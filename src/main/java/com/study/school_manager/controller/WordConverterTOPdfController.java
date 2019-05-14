package com.study.school_manager.controller;

import com.github.pagehelper.PageInfo;
import com.study.school_manager.annotation.OperationLog;
import com.study.school_manager.core.JSONResult;
import com.study.school_manager.core.Result;
import com.study.school_manager.core.jqGrid.JqGridResult;
import com.study.school_manager.dto.PdfParam;
import com.study.school_manager.entity.Pdf;
import com.study.school_manager.service.PdfService;
import com.study.school_manager.util.UploadFile;
import com.study.school_manager.util.WordPdfUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author sdy
 * @date 2019/4/9 13:24
 */
@Controller
@RequestMapping("/wordConverterPdf")
public class WordConverterTOPdfController extends BaseController{

    @Value("${word.location}")
    private String localPath;
    @Value("${fileurl}")
    private String nginxurl;

    @Resource
    private PdfService pdfService;

    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("menus",getMenus("wordConverterPdf"));
        model.addAttribute("nginxurl",nginxurl);
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
        String wordName = UploadFile.uploadWord(file);
        String name = wordName.substring(0,wordName.lastIndexOf("."));
        String target = localPath+File.separator+wordName;
        InputStream targetPath = new FileInputStream(target);
        File file1 = new File(localPath+File.separator+"pdf");
        if(!file1.exists()){
            file1.mkdir();
        }
        String outPath = localPath+File.separator+"pdf"+File.separator+name+".pdf";
        //保存路径
        Pdf pdf = new Pdf();
        pdf.setPath(outPath);
        pdf.setName(outPath.substring(outPath.lastIndexOf(File.separator)+1));
        pdfService.insert(pdf);
        OutputStream outDir = new FileOutputStream(outPath);
        PdfOptions pdfOptions = PdfOptions.create();
        Map<String,String> params = new HashMap<>();
        WordPdfUtils.wordConverterToPdf(targetPath,outDir,pdfOptions,params);
        return OK;
    }

    /***
     * 分页展示
     * @param param
     * @return
     */
    @RequestMapping("/grid")
    @ResponseBody
    public Result grid(PdfParam param){
        PageInfo<Pdf> pageInfo = pdfService.selectByPage(param);
        JqGridResult result = new JqGridResult();
        result.setRecords(pageInfo.getTotal());
        result.setTotal(pageInfo.getPages());
        result.setPage(pageInfo.getPageNum());
        result.setRows(pageInfo.getList());
        return new JSONResult(result);
    }

    @OperationLog("下载pdf")
    @RequestMapping(value = "/download")
    public void downloadPdf(String url,HttpServletResponse response) throws IOException {
        //获取文件流
        InputStream is = new URL(url).openStream();
        String name = url.substring(url.lastIndexOf("/")+1);
        response.reset();
        response.setContentType("application/pdf;charset=ISO8859-1");
        response.setHeader("content-disposition", "attachment; filename="+name);
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len=is.read(b))!=-1){
            os.write(b,0,len);
        }
    }

    @OperationLog("批量下载pdf")
    @RequestMapping("/downloadZip")
    public ResponseEntity<byte[]> downloadZip(HttpServletResponse response) throws IOException{
        /*String zipName = "pdf批量.zip";
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename="+zipName);*/
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        OutputStream os = response.getOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);
        List<Pdf> list = pdfService.selectAll();
        list.forEach(pdf -> {
            String path = pdf.getPath();
            String name = path.substring(path.lastIndexOf("/")+1);
            String url = nginxurl + path;
            try {
                InputStream is = new URL(url).openStream();
                zos.putNextEntry(new ZipEntry(name));
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes))!=-1){
                    zos.write(bytes,0,len);
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bos.close();
        zos.close();
        HttpHeaders headers = new HttpHeaders();
        String filename = new String("pdf批量.zip".getBytes("UTF-8"),"ISO-8859-1");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);
        return new ResponseEntity<byte[]>(bos.toByteArray(),headers, HttpStatus.CREATED);
    }
}
