package com.dmwys.photography.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 页面验证工具类
 *
 */
public class ValidateUtil {
	public static Logger LOG = LoggerFactory.getLogger(ValidateUtil.class);
	/**
	 * 判断是否选择了终端
	 * @param productStr:选择的终端数
	 * @param test_product:测试终端
	 * @return 返回错误页面
	 */
	public static View isProduct(String productStr,Integer test_product,HttpServletRequest req){
		if(StringUtils.isBlank(productStr) && (test_product==null || test_product==0)){
			req.setAttribute("message", "请选择终端");
			return new JspView("/common/message");
		}
		return null;
	}
	/**
	 * 判断是否输入了验证码
	 * @param req:请求
	 * @return 返回错误页面
	 */
	public static View isCode(HttpServletRequest req){
		String inCode = req.getParameter("validate_code");
		if(inCode == null || !inCode.equals(req.getSession().getAttribute("code"))){
			req.setAttribute("message", "请正确输入验证码!");
			return new JspView("/common/message");
		}
		req.removeAttribute("code");
		return null;
	}
	/**
	 * 根据信息返回页面
	 * @param msg:消息内容
	 * @param req:请求
	 * @param url:重定向的地址
	 * @return 返回消息页面
	 */
	public static View msg(String msg,String url,HttpServletRequest request){
	    request.setAttribute("message", msg);
		if(StringUtils.isNotBlank(url)){
		    request.setAttribute("url", url);
		}
		return new JspView("/common/message");
	}
	public static View msgBatch(String msg,String url,HttpServletRequest request){
	    request.setAttribute("message", msg);
		if(StringUtils.isNotBlank(url)){
		    request.setAttribute("url", url);
		}
		return new JspView("/common/batchImportMessage");
	}
	
	public static View msghistory(String msg,String url,HttpServletRequest request){
	    request.setAttribute("message", msg);
		if(StringUtils.isNotBlank(url)){
		    request.setAttribute("url", url);
		}
		return new JspView("/common/tips");
	}
	/**
	 * 根据信息返回页面
	 * @param msg:消息内容
	 * @param req:请求
	 * @return 返回消息页面
	 */
	public static View msg(String msg,HttpServletRequest request){
		return msg(msg,null,request);
	}
	public static View msghistory(String msg,HttpServletRequest request){
		return msghistory(msg,null,request);
	}
	public static void forward(String msg){
	    try {
	        Mvcs.getReq().setAttribute("message", msg);
	        Mvcs.getReq().getRequestDispatcher("/common/message.jsp").forward(Mvcs.getReq(),Mvcs.getResp());
        } catch (Exception e) {
            LOG.error("重定向错误",e);
        }
	}
	
	public static void uiaforward(String msg){
	    try {
	        Mvcs.getReq().setAttribute("message", msg);
	        Mvcs.getReq().getRequestDispatcher("/common/tips.jsp").forward(Mvcs.getReq(),Mvcs.getResp());
        } catch (Exception e) {
            LOG.error("重定向错误",e);
        }
	}
	
	public static void msghistoryback(String msg,HttpServletRequest request){
		  try {
		        Mvcs.getReq().setAttribute("message", msg);
		        Mvcs.getReq().getRequestDispatcher("/common/tips.jsp").forward(Mvcs.getReq(),Mvcs.getResp());
	        } catch (Exception e) {
	            LOG.error("重定向错误",e);
	        }
	}
}
