package com.dmwys.photography.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SMSSender {
	private static SMSSender sender;

	private SMSSender() {
	}

	public static SMSSender getSMSSender() {
		if (sender == null) {
			sender = new SMSSender();
		}
		return sender;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public int sendSms(String mobiles, String text) {
		SendThread t = new SendThread(mobiles, text);
		t.start();
		return 0;
	}
	public static void main(String[] aa){
	    SMSSender.getSMSSender().sendSms("13426409323", "tstete");
	}
}

class SendThread extends Thread {
	private static final String user_name = "kuyun";
	private static final String password = "kuyun1234";
	private static final String key = "640917f08a47990b06bb5dfe6e9d3db1";

	String mobiles;
	String text;
	public SendThread(String mobiles,String text) {
		this.mobiles = mobiles;
		this.text = text;
	}

	public void run() {
		String sign = " 【酷云】";
		if (text == null || text.equals("")) {
			return;
		}
		if (mobiles == null || mobiles.equals("")) {
			return;
		}
		text += sign;
		// 创建StringBuffer对象用来操作字符串
		StringBuffer sb = new StringBuffer("http://m.5c.com.cn/api/send/?");
		// APIKEY
		sb.append("apikey=").append(key);
		// 用户名
		sb.append("&username=").append(user_name);
		// 向StringBuffer追加密码
		sb.append("&password=").append(password);
		// 向StringBuffer追加手机号码
		sb.append("&mobile=").append(mobiles);
		// 向StringBuffer追加消息内容转URL标准码
		String result = null;
		try {
			sb.append("&content=" + URLEncoder.encode(text, "GBK"));
			// 创建url对象
			URL url = new URL(sb.toString());
			// 打开url连接
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			// 设置url请求方式 ‘get’ 或者 ‘post’
			connection.setRequestMethod("POST");
			// 发送
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			// 返回发送结果
			result = in.readLine();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
