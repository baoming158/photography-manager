package com.dmwys.photography.util;
import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 卡片相关的常量定义类
 */
public class Constants {

	private static Map<Integer, String> ERROR_CODE = new HashMap<Integer, String>();
	// 分页相关
	public static final int PAGE_SIZE = 10;//分页每页显示条目
	public static final int VIEWPAGE_SIZE = 2;//针对每页页码左右偏移量数
	public static final int ERROR_0 = 0;//成功
	// 用户登录相关
	public static final int ERROR_1001 = 1001;//用户不存在
	public static final int ERROR_1002 = 1002;//用户密码不正确
	public static final int ERROR_1003 = 1003;//用户名不能为空
	public static final int ERROR_1004 = 1004;//用户密码不能为空
	public static final int ERROR_1005 = 1005;//验证码不能为空
	public static final int ERROR_1006 = 1006;//验证码无效或过期
	public static final int ERROR_1007 = 1007;//用户名或密码错误
	public static final int ERROR_1008 = 1007;//UIA鉴权失败
	//卡片属性相关
	public static final int GLOBAL_TYPE = 0;//全局卡片类型
	public static final int COLUMN_TYPE = 1;//针对栏目的卡片类型
	
	public static final String WordSensor = "wordSensor";
	

	public enum CONNECTION_SHOW_TYPE { BANNER_JUMP, CARD_JUMP, TURN_JUMP, B_CON_JUMP};
	
	
	public static final String LoginInfoPrefex = "LoginMessage:";//针对栏目的卡片类型
	
	static {
		ERROR_CODE.put(ERROR_0, "成功");
		ERROR_CODE.put(ERROR_1001, "用户不存在");
		ERROR_CODE.put(ERROR_1002, "用户密码不正确");
		ERROR_CODE.put(ERROR_1003, "用户名不能为空");
		ERROR_CODE.put(ERROR_1004, "用户密码不能为空");
		ERROR_CODE.put(ERROR_1005, "验证码不能为空");
		ERROR_CODE.put(ERROR_1006, "验证码无效或过期");
		ERROR_CODE.put(ERROR_1007, "用户名或密码错误");
		ERROR_CODE.put(ERROR_1008, "UIA鉴权失败");
	}
	public static String getErrorCodeInfo(int result_code) {
		if (!ERROR_CODE.containsKey(result_code)) {
			return "未知错误";
		}
		return ERROR_CODE.get(result_code);
	}
}
