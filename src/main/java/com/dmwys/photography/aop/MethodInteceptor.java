package com.dmwys.photography.aop;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.lang.Lang;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 敏感词汇过滤
 */
public class MethodInteceptor implements MethodInterceptor {

	public static Logger logger =  LoggerFactory.getLogger(MethodInteceptor.class);
	public void filter(InterceptorChain chain) throws Throwable {
		try {
	        chain.doChain();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("拦截出错",e);
			throw Lang.wrapThrow(e);
		} finally {
		}
	}
}
