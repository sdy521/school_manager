package com.study.school_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.school_manager.core.BaseDao;
import com.study.school_manager.core.BaseService;
import com.study.school_manager.dao.PdfDao;
import com.study.school_manager.dto.PdfParam;
import com.study.school_manager.entity.Pdf;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sdy
 * @date 2019/4/30 14:29
 */
@Service
public class PdfService extends BaseService<Pdf> {
    @Resource
    private PdfDao pdfDao;
    @Override
    protected BaseDao<Pdf> getDao() {
        return pdfDao;
    }

    public PageInfo<Pdf> selectByPage(PdfParam param){
        PageHelper.startPage(param.getPage(),param.getRows());
        List<Pdf> list = pdfDao.selectByPage();
        return new PageInfo<>(list);
    }
}
