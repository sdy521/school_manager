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
import java.util.Map;

@Controller
@RequestMapping(value = "/excel")
public class ExcelController extends BaseController{
    @Resource
    private InfoService infoService;

    /**
     * 导出教师信息
     * @return
     */
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<Map> list = infoService.selectInfoDetail(1);

        //excel标题
        String[] title = {"姓名","性别","年龄","手机号","地址"};

         //excel文件名
         String fileName = "教师信息表"+System.currentTimeMillis()+".xls";

          //sheet名
            String sheetName = "教师信息表";

            String[][] content = new String[title.length][5] ;

            for (int i = 0; i < list.size(); i++) {
//                    content[i] = new String[title.length];
                    Map map = list.get(i);
                    content[i][0] = map.get("name").toString();
                    Integer sex = (Integer) map.get("sex");
                    if(sex==0){
                        content[i][1] = "女";
                    }else {
                        content[i][1] = "男";
                    }
                    content[i][2] = map.get("age").toString();
                    content[i][3] = map.get("phone").toString();
                    content[i][4] = map.get("address").toString();
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

    /**
     * 导出学生信息
     * @return
     */
    @RequestMapping(value = "/exportStudent")
    @ResponseBody
    public void exportStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取数据
        List<Map> list = infoService.selectInfoDetail(2);

        //excel标题
        String[] title = {"姓名","性别","年龄","手机号","地址"};

        //excel文件名
        String fileName = "学生信息表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "学生信息表";

        String[][] content = new String[title.length][5] ;

        for (int i = 0; i < list.size(); i++) {
//                    content[i] = new String[title.length];
            Map map = list.get(i);
            content[i][0] = map.get("name").toString();
            Integer sex = (Integer) map.get("sex");
            if(sex==0){
                content[i][1] = "女";
            }else {
                content[i][1] = "男";
            }
            content[i][2] = map.get("age").toString();
            content[i][3] = map.get("phone").toString();
            content[i][4] = map.get("address").toString();
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
