package com.study.study_manager.test;

import com.study.study_manager.util.WordPdfUtils;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sdy
 * @date 2019/4/8 16:50
 */
public class TestWordConverterPdf {
    public static void main(String[] args) throws Exception{
        String filepath = "D:\\pdf\\test.docx";
        String outpath = "D:\\pdf\\test.pdf";

        InputStream source = new FileInputStream(filepath);
        OutputStream target = new FileOutputStream(outpath);
        Map<String, String> params = new HashMap<String, String>();
        params.put("SpringSecurity","ss");

        PdfOptions options = PdfOptions.create();

        WordPdfUtils.wordConverterToPdf(source, target, options, params);
    }
}
