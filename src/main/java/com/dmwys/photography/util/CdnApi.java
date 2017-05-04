/**
 * 
 */
package com.dmwys.photography.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * @version 创建时间：2014-7-31 下午5:45:37 
 */
public class CdnApi {

	public static Logger logger = LoggerFactory.getLogger(CdnApi.class);

	public static String REQUEST_REFRESH_URL="https://r.chinacache.com/content/refresh";
	
	public static final String CDN_USERNAME=ResourceHelper.get("cdn.cdn_username");
	public static final String CDN_PASSWORD=ResourceHelper.get("cdn.cdn_password");
	
	
	public static void main(String[] aa) throws Exception{
//		String[] dirs = new String[]{
//				"http://logoresource.tv.kuyun.com/aa.jpg",
//				"http://logoresource.tv.kuyun.com/adpro/3/"
//		};
		CdnApi.commitCDN("http://logoresource.tv.kuyun.com/cardsys_api/xwqtest/a.txt");
	}
	/**
	 * 将本地资源提交到cdn
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static boolean commitCDN(String dirs,String urls) throws Exception{
		return commitCDN(new String[]{dirs},new String[]{urls});
	}
	public static boolean commitCDN(String dirs) throws Exception{
		return commitCDN(new String[]{dirs},null);
	}
		@SuppressWarnings("unchecked")
	public static boolean commitCDN(String[] dirs,String[] urls) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Gson g = new Gson();
		map.put("urls",urls);
		map.put("dirs",dirs);
		map.put("callback",null);
		String task = g.toJson(map);
		System.out.println(task);
		System.out.println(new Gson().toJson(task));
		NameValuePair[] param = new NameValuePair[]{new NameValuePair("username",CDN_USERNAME),
			new NameValuePair("password",CDN_PASSWORD),
			new NameValuePair("task",task)
		};
//		String response = HttpUtils.request(REQUEST_REFRESH_URL, param);
		String response = null;
		Map<String,String> resp = g.fromJson(response, Map.class);
		String r_id= resp.get("r_id");
		String urlexceed= resp.get("urlexceed");
		String direxceed= resp.get("direxceed");
		if(StringUtils.isNotBlank(r_id)){
			if(StringUtils.isNotBlank(urlexceed)){
				int status=Integer.parseInt(urlexceed);
				if(status<0){
					logger.error("CDN访问超过一个小时的量");
				}else{
				    logger.error("CDN访问超过一天的量");
				}
				return false;
			}
			if(StringUtils.isNotBlank(direxceed)){
				int status=Integer.parseInt(direxceed);
				if(status<0){
				    logger.error("超过当前10分钟规定的量");
				}else{
				    logger.error("没有目录刷新的权限");
				}
				return false;
			}
			logger.info("CDN刷新成功 ：" + task);
			return true;
		}
		return false;
	}
	
}


