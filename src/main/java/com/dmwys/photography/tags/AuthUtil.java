package com.dmwys.photography.tags;

import java.util.List;

import javax.servlet.http.HttpSession;

@SuppressWarnings("unchecked")
public class AuthUtil {
	private static List<String> auth_list = null;
	private static List<String> all_auth_list = null;
	public static boolean hasAuth(HttpSession session,String value){
		auth_list = (List<String>) session.getAttribute("accessAuth");
		all_auth_list = (List<String>) session.getAttribute("accessAllAuth");
		if(all_auth_list == null || !all_auth_list.contains(value)){//如果没有配置该权限，则不再管控范围内
			return true;
		}
		if(auth_list == null || auth_list.size() == 0){//如果不做权限配置 则拥有所有的权限
			return true;
		}else{
			if(auth_list.contains(value)){
				return true;
			}else{
				return false;
			}
			
		}
	}
	/**
	 * 如果没有配置 则不拥有该权限 用于标签权限 ，控制那些默认就不拥有的权限
	 * @param session
	 * @param value
	 * @return
	 */
	public static boolean hasAuthExclude(HttpSession session,String value){
		auth_list = (List<String>) session.getAttribute("accessAuth");
		all_auth_list = (List<String>) session.getAttribute("accessAllAuth");
		if(all_auth_list == null || !all_auth_list.contains(value)){//如果没有配置该权限，则不再管控范围内
			return false;
		}
		if(auth_list == null || auth_list.size() == 0){//如果不做权限配置 则不拥有该权限
			return false;
		}else{
			if(auth_list.contains(value)){
				return true;
			}else{
				return false;
			}
			
		}
	}
}
