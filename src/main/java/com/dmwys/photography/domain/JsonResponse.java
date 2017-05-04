package com.dmwys.photography.domain;


public class JsonResponse {
	private int code = 0;
	private String msg ;
	private Object data;
	public static JsonResponse Err = Err("操作失败");
	public static JsonResponse Ok = Ok("操作成功");
	public static JsonResponse Ok(String msg){
		return new JsonResponse(0,msg,null);
	}
	public static JsonResponse Err(String msg){
		return new JsonResponse(-1,msg,null);
	}
	public static JsonResponse Data(Object data){
		return new JsonResponse(0,"操作成功",data);
	}
	private JsonResponse(int code ,String msg,Object data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
