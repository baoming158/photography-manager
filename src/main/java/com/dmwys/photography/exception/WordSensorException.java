package com.dmwys.photography.exception;
/**
 * 
 * @author admin
 *
 */
public class WordSensorException extends RedirectException {
	public String url = "/common/exception.jsp";
	private static final long	serialVersionUID	= 1L;
	public WordSensorException(){
		super("匹配到敏感词");
	}
	public WordSensorException(String message){
		super(message);
	}
	public String url(){
		return url;
	}
}
