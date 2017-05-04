package com.dmwys.photography.login;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dmwys.photography.domain.JsonResponse;
import com.dmwys.photography.domain.UserImpl;
import com.dmwys.photography.services.PhotoService;
import com.dmwys.photography.util.*;
import org.nutz.dao.Cnd;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import net.sf.json.JSONObject;


@IocBean
@InjectName("loginAction") 
public class LoginAction {	
	Logger LOG = LoggerFactory.getLogger(LoginAction.class);

	@Inject(value = "refer:photoService")
	private PhotoService photoService;

	@At("/login")
	@Ok("raw")
	public String login(@Param("username") String username, @Param("password")String password,  HttpServletRequest request) {
		Integer code=0;
		JSONObject json = new JSONObject();
		json.set("TimeStamp", new Date().getTime());
		json.set("ret", code);
		json.set("message", Constants.getErrorCodeInfo(code));
		SqlExpressionGroup group = Cnd.exps("deleted", "=", "0");
		group.and("username", "=", username);
		group.and("password", "=", MD5Util.getMd5(password.getBytes()));
		List<UserImpl> list = photoService.search(UserImpl.class, Cnd.where(group));
		UserImpl user = null;
		if(list != null && list.size() > 0){
			user = list.get(0);
		}
		//从UIA获取权限
        if(user == null){
            json.set("ret", Constants.ERROR_1008);
            json.set("message", Constants.getErrorCodeInfo(Constants.ERROR_1008));
            return json.toString();
        }
        request.getSession().setAttribute("user", user);
        request.getSession().setMaxInactiveInterval(60000);//6分钟不操作sessioin失效
//        loadAccessAuth(request,user);
		//登陆成功之后保存相关信息
		return json.toString();
	}

	// 将权限信息放到session
	private void loadAccessAuth(HttpServletRequest request, UserImpl user) {
		List<String> list = UIAUtil.loadAccessAuth(user.getId(), ResourceHelper.get("uia.app_id"));
		List<String> all_list = UIAUtil.loadAllAccessAuth(ResourceHelper.get("uia.app_id"));
		System.out.println("set accessAuth -->" + new Gson().toJson(list));
		System.out.println("set accessAllAuth -->" + new Gson().toJson(all_list));
		request.getSession().setAttribute("accessAuth", list);
		request.getSession().setAttribute("accessAllAuth", all_list);
	}
	@At("/clearLogin")
	@Ok("jsp:/login")
	public void clear(HttpServletRequest req, @Attr("user")
	UserImpl user) {
		LOG.info("LoginAction.clearLogin|name:" + user.getChName() + "|IP:"
				+ IPUtil.getRemoteAddr(req));
		req.getSession().invalidate();
	}
	
	
	@At("/noright_json")
    @Ok("json")
    public JsonResponse noRightJson() {
        return JsonResponse.Err("您没有访问权限");
    }
}
