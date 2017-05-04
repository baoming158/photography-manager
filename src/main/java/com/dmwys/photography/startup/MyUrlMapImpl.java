package com.dmwys.photography.startup;


import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.nutz.mvc.ActionChainMaker;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.impl.UrlMappingImpl;

public class MyUrlMapImpl extends UrlMappingImpl {
	private static Set<String> set= new HashSet<String>();
	
	/**
	 * 添加URL时初始化权限
	 */
	@Override
	public void add(ActionChainMaker maker, ActionInfo ai, NutConfig config) {
		super.add(maker, ai, config);
		Method method = ai.getMethod();
		At a = method.getAnnotation(At.class);
		if (null == a) {
			return;
		}
		String[] value = a.value();
		if(set.contains(value[0])){
			System.err.println("duplicated url mapping : " + value[0]);
		}
		set.add(value[0]);
	}
}
