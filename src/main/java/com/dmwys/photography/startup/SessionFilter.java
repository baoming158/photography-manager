package com.dmwys.photography.startup;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.dmwys.photography.util.Constants;
import com.dmwys.photography.util.IPUtil;
import org.apache.commons.lang3.StringUtils;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;

import com.dmwys.photography.domain.UserImpl;

public class SessionFilter implements ActionFilter  {
	private static final List<String> excludeURI = new ArrayList<String>(){
		private static final long serialVersionUID = 1L;
		{
			add("/login");
			add("/sendServlet");
			add("/all_videos");
			add("/all_dots");
			add("/video/label");
			add("/video/label/second");
			add("/video/label/child");
			add("/ad/videos");
		}};
		@Override
		public View match(ActionContext ctx) {
			try{
				String modifyId = ctx.getRequest().getParameter("modifyId");//来自管理平台的请求
				UserImpl userInfo = (UserImpl) ctx.getRequest().getSession().getAttribute("user");
				HttpSession sesion = ctx.getRequest().getSession();
				System.out.println(sesion.getId());
				String base = ctx.getRequest().getServletPath();
				if(StringUtils.isNotBlank(modifyId) || excludeURI.contains(base)){//登录请求
					return null;
				}
				if(userInfo == null){
					return new JspView("/common/dealSession");
				}

			    String key = Constants.LoginInfoPrefex + ":" + userInfo.getName() + ":IP" + IPUtil.getRemoteAddr(ctx.getRequest()) + ":" + sesion.getId();//ip地址+sessionId
			   /* Object o= RedisUtil.getItem(key);//获取会话信息
                if(o == null){//如果没有就返回登录页
                    CardsLog.INFO.info("记录用户非法会话|key="+key);
                    return new JspView("/common/dealSession");
                }*/
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
}
