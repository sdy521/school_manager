package com.study.study_manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.study_manager.core.BaseDao;
import com.study.study_manager.core.BaseService;
import com.study.study_manager.dao.PdfDao;
import com.study.study_manager.dto.PdfParam;
import com.study.study_manager.entity.Pdf;
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
