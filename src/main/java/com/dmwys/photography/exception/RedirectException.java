package com.dmwys.photography.exception;

/**
 * 抛出该异常将会在页面中重定向
 * @author admin
 *
 */
public class RedirectException extends RuntimeException{
	private static final long	serialVersionUID	= 1L;
	protected String url  = "/common/exception.jsp";
	public RedirectException(){
		super();
	}
	public RedirectException(String message){
		super(message);
	}
	public String url(){
		return url;
	}
}
