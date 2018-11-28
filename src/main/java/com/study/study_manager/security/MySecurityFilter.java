package com.study.study_manager.security;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.stereotype.Service;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

@Service
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {//AbstractSecurityInterceptor 、Filter（自定义的过滤器）
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
