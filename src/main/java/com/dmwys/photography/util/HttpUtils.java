package com.dmwys.photography.util;


import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang3.StringUtils;
import org.nutz.http.HttpException;


public class HttpUtils {

	
	/*public static String request(String url,NameValuePair[] param) throws Exception{
		HttpClient client = new HttpClient();// 定义client对象
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");// 指定传送字符集为GBK格式
		client.getHttpConnectionManager().getParams().setConnectionTimeout(1000);// 设置连接超时时间为1秒（连接初始化时间）
		PostMethod method = new PostMethod(url);
		if(param != null){
			method.setRequestBody(param);
		}
		int state=client.executeMethod(method);
		byte[] response = method.getResponseBody();
		client.getHttpConnectionManager().closeIdleConnections(1);
		String result = new String(response);
		if(state != 200){
			throw new Exception("请求失败URL："+ url + "  state :" + state + " response : "  + result);
		}
		System.out.println("state:" + state + " response:" + result);
		return result;
	}
	*/
    /**
     * 功能：发送一个请求，获取请求后的数据
     * 
     * @author: zhijian.zhang
     * @Date: 2011-6-17
     * 
     * @param sUrl
     *            连接URL
     * @param param
     *            参数
     * @return
     * @throws IOException
     */
    public static String doPostUrl(String sUrl, String param, String encode) throws Exception{
        URL url = null;
        BufferedReader br = null;
        InputStream in = null;
        try {
            url = new URL(sUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            // 设置超时时间为1分钟
            urlConnection.setConnectTimeout(60000);
            if (StringUtils.isNotBlank(param)) {
                OutputStream raw = urlConnection.getOutputStream();
                OutputStream buf = new BufferedOutputStream(raw);
                OutputStreamWriter out = new OutputStreamWriter(buf, encode);
                out.write(param);
                out.flush();
                out.close();
            }
            in = urlConnection.getInputStream();
            if (StringUtils.isNotBlank(encode)) {
                br = new BufferedReader(new InputStreamReader(in, encode));
            } else {
                br = new BufferedReader(new InputStreamReader(in));
            }
            StringBuffer sb = new StringBuffer();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            br.close();
            in.close();
            return sb.toString();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 
     * 功能：处理抓取返回页面数据类型为Deflate压缩数据内容
     * 
     * @author: zj.zhang
     * @Date: 2012-6-20
     * 
     * @param url
     *            请求的URL
     * @param initByteLenth
     *            初始byte数据长度
     * @return
     */
    public static String httpClientDeflatePost(String url, int initByteLenth) {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        try {
            client.executeMethod(get);
            byte[] iStream = get.getResponseBody();
            int initLength = 2048;
            if (initByteLenth > 0) {
                initLength = initByteLenth;
            }
            byte[] outBytes = new byte[initLength];
            Inflater inflater = new Inflater(true);
            inflater.setInput(iStream);
            try {
                inflater.inflate(outBytes);
                return new String(outBytes);
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
        } catch (HttpException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            get.releaseConnection();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        try {
            String result = HttpUtils.doPostUrl("http://card.tvapi.letv.com/iptv/api/card/noticeLetv","cardIds=subscribe","gb2312");
            System.out.println(result);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
}
