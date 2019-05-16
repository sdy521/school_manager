package com.study.school_manager.security.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/***
 * 自定义过滤器
 */
@Component
public class MySecurityFilter extends AbstractSecurityInterceptor implements
        Filter {
	@Autowired
	private MySecurityMetadataSource mySecurityMetadataSource;
	@Autowired
	private MyAccessDecisionManager myAccessDecisionManager;
	@Autowired
	private AuthenticationManager myAuthenticationManager;

	@PostConstruct
	public void init() {
		super.setAuthenticationManager(myAuthenticationManager);
		super.setAccessDecisionManager(myAccessDecisionManager);
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.mySecurityMetadataSource;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

}