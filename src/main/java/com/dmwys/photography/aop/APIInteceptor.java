package com.dmwys.photography.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.lang.Lang;
import org.nutz.mvc.Mvcs;

public class APIInteceptor implements MethodInterceptor {

	public void filter(final InterceptorChain chain) {
			try {
				HttpServletRequest request = Mvcs.getReq();
				request.setAttribute("cmd", request.getServletPath());
				request.setAttribute("timeStamp",new Date());
				request.setAttribute("result", "OK");
				request.setAttribute("result_code", "0");
				chain.doChain();
			} catch (Throwable e) {
				throw Lang.wrapThrow(e);
			}
	}

}
