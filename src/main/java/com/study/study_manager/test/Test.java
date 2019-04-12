package com.study.study_manager.test;

import java.io.IOException;

/**
 * @author sdy
 * @date 2019/4/12 14:13
 */
public class Test {
    public static void main(String[] args) {
        String path="C:\\Users\\Administrator.ZYDN-20180527PO\\Desktop\\filedesk\\说明书2.docx";
        try {
            Process p = Runtime.getRuntime().exec("cmd /c cmd.exe /c " + path+" exit");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
