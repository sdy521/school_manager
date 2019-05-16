package com.study.school_manager.security.handler;

import com.study.school_manager.dao.MenuDao;
import com.study.school_manager.entity.Menu;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;


/**
 * 加载资源与权限的对应关系
 *
 * */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Resource
	private MenuDao menuDao;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	/***
	 * @PostConstruct 加载servlet时加载   在构造方法之后加载
	 */
	@PostConstruct
	private void loadResources() {
		if (resourceMap == null) {
			List<Menu> menus = menuDao.selectAll();
			resourceMap = new HashMap<>();
			for (Menu m : menus) {
				//将所有url增加权限  url为键  权限（ROLE_）为值
				Collection<ConfigAttribute> configAttributes = new ArrayList<>();
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + m.getCode());
				configAttributes.add(configAttribute);
				resourceMap.put(m.getUrl(), configAttributes);
			}
		}
	}

	/**
	 * 根据url 获取需要的权限  返回需要的权限
	 * @param object
	 * @throws IllegalArgumentException
	 *
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if (resourceMap == null) {
			loadResources();
		}
		//去除参数  获取上方存储的key
		if (requestUrl.contains("?")) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		return resourceMap.get(requestUrl);
	}

	/**
	 * 刷新权限
	 */
	public synchronized void refreshResources() {
		//TODO 线程问题：比如说这里设为null，但是其他线程正在使用resourceMap
		resourceMap = null;
		loadResources();
	}

}