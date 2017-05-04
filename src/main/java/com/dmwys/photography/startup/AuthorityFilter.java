package com.dmwys.photography.startup;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;

public class AuthorityFilter implements ActionFilter  {
		@SuppressWarnings("unchecked")
		@Override
		public View match(ActionContext ctx) {
			HttpSession session = ctx.getRequest().getSession();
			List<String> auth_list = (List<String>) session.getAttribute("accessAuth");
			List<String> all_auth_list = (List<String>) session.getAttribute("accessAllAuth");
			String base = ctx.getRequest().getServletPath();
			if(all_auth_list == null || !all_auth_list.contains(base)){//如果没有配置该权限，则不再管控范围内
				return null;
			}
			View view = getView(ctx);
			if(auth_list == null || auth_list.size() == 0){//如果不做权限配置 则拥有所有的权限
				return null;
			}else{
				if(auth_list.contains(base)){
					return null;
				}else{
					return view;
				}
				
			}
		}

		private View getView(ActionContext context) {
			View view = new JspView("/common/403");
			Ok ok = context.getMethod().getAnnotation(Ok.class);
			if(ok != null && "json".equals(ok.value())){
	        	view = new ServerRedirectView("/noright_json");
			}
			return view;
		}
}
