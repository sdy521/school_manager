package com.study.study_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.UserDao;
import com.study.study_manager.dto.TeacherParam;
import com.study.study_manager.entity.User;
import com.study.study_manager.util.UploadFile;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentListService extends BaseService<User> {
    @Resource
    private UserDao userDao;

    @Override
    protected BaseDao<User> getDao() {
        return userDao;
    }
    public PageInfo<User> selectByPage(TeacherParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<User> list = userDao.selectByPage(param.getName(),param.getDeleted(),param.getSord(),2);
        return new PageInfo<User>(list);
    }

    public void recover(User teacher){
        teacher.setDeleted(false);
        teacher.setUpdateTime(new Date());
        userDao.updateByPrimaryKeySelective(teacher);
    }

    /***
     * 解析excel
     * @param filename
     * @param enable
     */
    public boolean importExcel(String filename,boolean enable){
        List<User> list = new ArrayList<>();
        String filePath = UploadFile.uploadRoute()+filename;
        Cell cell=null;
        Sheet sheet=null;
        Workbook book=null;
        String content="";//每个单元格内容
        File newFile = new File(filePath);
        try {
            book = new XSSFWorkbook(new FileInputStream(newFile));//2007和之后的版本
        } catch (Exception e) {
            try {
                book = new HSSFWorkbook(new FileInputStream(newFile));//2007之前的版本
            } catch (Exception e1) {
                e1.printStackTrace();
                return false;
            }
        }
        FileOutputStream os=null;//更新内容
        try {
            os = new FileOutputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sheet = book.getSheetAt(0);//获取第一张表
        //行数(表头的目录不需要，从1开始)
        for(int i=1;i<sheet.getLastRowNum()+1;i++){
            Integer number = i;
            User user = new User();
            //列数
            Row row = sheet.getRow(i);
            for(int j=0;j<row.getLastCellNum();j++){
                cell = row.getCell(j);
                if(cell==null)continue;//获取第i行，第j列的值,如果有空,则换行继续读直到结束
                cell.setCellType(Cell.CELL_TYPE_STRING);//设置单元格类型为字符串
                content = cell.getStringCellValue();
                if(!StringUtils.isEmpty(content)){//内容不为空就解析
                    if(j==0){
                        user.setName(content);
                    }else {
                        user.setType(Integer.parseInt(content));
                    }
                }else {
                    break;
                }
            }
            if(user.getName()==null)continue;
            user.setEnable(enable);
            user.setPassword(BCrypt.hashpw("123",BCrypt.gensalt()));
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setDeleted(false);
            user.setImg(null);
            //插入结果
            Cell createCell = row.createCell(2);
            createCell.setCellType(Cell.CELL_TYPE_STRING);
            createCell.setCellValue("导入成功");
            list.add(user);
        }
        if (!CollectionUtils.isEmpty(list)){
            userDao.insertList(list);
        }
        try {
            book.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
