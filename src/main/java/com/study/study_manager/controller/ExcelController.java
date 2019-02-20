package com.study.study_manager.controller;

import com.study.study_manager.entity.Info;
import com.study.study_manager.service.InfoService;
import com.study.study_manager.util.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "/excel")
public class ExcelController extends BaseController{
    @Resource
    private InfoService infoService;

    /**
     * 导出报表
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<Info> list = infoService.selectAll();

        //excel标题
        String[] title = {"名称","性别","年龄","地址","手机号"};

         //excel文件名
         String fileName = "学生信息表"+System.currentTimeMillis()+".xls";

          //sheet名
            String sheetName = "学生信息表";

            String[][] content = new String[title.length][5] ;

            for (int i = 0; i < list.size(); i++) {
//                    content[i] = new String[title.length];
                    Info info = list.get(i);
                    content[i][0] = info.getUserid().toString();
                    content[i][1] = info.getSex().toString();
                    content[i][2] = info.getAge().toString();
                    content[i][3] = info.getAddress().toString();
                    content[i][4] = info.getPhone().toString();
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

            //响应到客户端
            try {
                    this.setResponseHeader(response, fileName);
                    OutputStream os = response.getOutputStream();
                    wb.write(os);
                    os.flush();
                    os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
