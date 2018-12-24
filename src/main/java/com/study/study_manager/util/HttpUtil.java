package com.study.study_manager.util;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("x-requested-with"));
    }
}
