package com.dmwys.photography.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Throwables;
/**
 * 
 * 加载reload.properties配置文件类
 * 
 */
public class PropertyHelper {
    private static final int reloadable_time =  5 * 1000;
	private static Properties prop = new Properties();;
	private static InputStream inputStream;
	private static long last_time = 0L;
	private static void reload(){
		try {
			inputStream = PropertyHelper.class.getResourceAsStream("/reload.properties");
			prop.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
            Throwables.propagate(e);
		}finally{
		    if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Throwables.propagate(e);
                }
            }
		}
	}
	public static String get(String key) {
		if(System.currentTimeMillis() - last_time > reloadable_time){
			reload();
			last_time = System.currentTimeMillis();
		}
		String value =  prop.getProperty(key);
		try {
			return new String(value.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
            Throwables.propagate(e);
		}
		return null;
	}
	public static Boolean getBoolean(String key) {
		if(System.currentTimeMillis() - last_time > 5 * 1000){
			reload();
			last_time = System.currentTimeMillis();
		}
		String value =  prop.getProperty(key);
		if(value.equalsIgnoreCase("true")){
			return true;
		}
		if(value.equalsIgnoreCase("false")){
			return false;
		}
		return null;
	}
	
	public static void AddKeysAndValues(HttpServletRequest request) {  
        if(System.currentTimeMillis() - last_time > reloadable_time){
            reload();
            last_time = System.currentTimeMillis();
        }
        Iterator<Entry<Object, Object>> it = prop.entrySet().iterator();  
        while (it.hasNext()) {  
            Entry<Object, Object> entry = it.next();  
            Object key = entry.getKey();  
            Object value = entry.getValue();
            String k = key.toString().replace(".", "_");
            request.getSession().setAttribute(k, value);
        }  
    }  
	public static void main(String[] a) throws InterruptedException{
		while(true){
			String id = PropertyHelper.get("sendSMS");
			System.out.print(id + "\t");
			Thread.sleep(2000);
		}
	}
}
