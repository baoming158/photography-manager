package com.dmwys.photography.startup;

import javax.servlet.http.HttpServletRequest;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;

public class RequestFilter implements ActionFilter  {

	@Override
	public View match(ActionContext ctx) {
		HttpServletRequest request = ctx.getRequest();
//		if(request.getMethod().equalsIgnoreCase("get")){
//			ctx.setRequest(new HttpGetServletRequestWrapper(request,"UTF-8"));
//		}
		ctx.setRequest(new ServletSettingWrapper(request));
		return null;
	}
}
