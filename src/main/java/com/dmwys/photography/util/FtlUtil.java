package com.dmwys.photography.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FtlUtil {
	public static final String	source_folder	= "template";
	private static String		path;
	private static Logger		logger			= LoggerFactory.getLogger(FtlUtil.class);

	public static String ftl2String(Template template, Object context) {
		String resultString = "";
		StringWriter writer = new StringWriter();
		try {
			template.process(context, writer);
			resultString = writer.toString();
		} catch (Exception e) {
			logger.error("freemaker 解析失败 ",e );
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				logger.error("writer 关闭异常 ",e );
			}
		}
		return resultString;
	}

	@SuppressWarnings("unchecked")
	public static String ftl2String(Template template, HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		Enumeration<String> querynames = request.getParameterNames();
		while(querynames.hasMoreElements()){
			String key = null;
			String value = request.getParameter(key = querynames.nextElement());
			map.put(key, value);
		}
		Enumeration<String> attrnames = request.getAttributeNames();
		while(attrnames.hasMoreElements()){
			String key = null;
			Object value = request.getAttribute(key = attrnames.nextElement());
			map.put(key, value);
		}
		return ftl2String(template, map);
	}
	
	@SuppressWarnings("unchecked")
	public static String ftl2String(Template template,Map<String,Object> map, HttpServletRequest request) {
		Map<String,Object> contentMap = new HashMap<String,Object>();
		Enumeration<String> querynames = request.getParameterNames();
		while(querynames.hasMoreElements()){
			String key = null;
			String value = request.getParameter(key = querynames.nextElement());
			contentMap.put(key, value);
		}
		request.setAttribute("queryString", argsFactory(contentMap));
		Enumeration<String> attrnames = request.getAttributeNames();
		while(attrnames.hasMoreElements()){
			String key = null;
			Object value = request.getAttribute(key = attrnames.nextElement());
			contentMap.put(key, value);
		}
		if(map != null){
			contentMap.putAll(map);
		}
		String result = ftl2String(template, contentMap);
//		result = result.replaceAll("\n\r", "");
//		result = result.replaceAll("\r\n", "");
//		result = result.replaceAll("\n", "");
//		result = result.replaceAll("\t", "    ");
		return result; 
	}

	
	private static String argsFactory(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		final String and = "&";
		final String eq = "=";
		if (map != null && map.size() > 0) {
			Set<Map.Entry<String, Object>> set = map.entrySet();
			for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it
					.hasNext();) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it
						.next();
				if (entry.getValue() != null && !entry.getValue().equals("")) {
					sb.append(and);
					sb.append(entry.getKey());
					sb.append(eq);
					sb.append(entry.getValue());
				} else {
				}
			}
		}
		return sb.toString();
	}
	
	public static Template getTemplate(String ftlName) {
		Template template = null;
		try {
			initPath();
			// 创建Configuration对象
			Configuration cfg = new Configuration();
			// 设置FreeMarker的模版文件位置
			cfg.setDirectoryForTemplateLoading(new File(path));
			cfg.setObjectWrapper(new BeansWrapper());
			cfg.setEncoding(Locale.getDefault(), "utf-8");
			cfg.setDateFormat("yyyy-MM-dd");
			cfg.setTimeFormat("yyyy-MM-dd HH:mm:ss");
			cfg.setWhitespaceStripping(true);
			cfg.setBooleanFormat("true,false");
			// 创建Template对象
			template = cfg.getTemplate(ftlName);
			template.setEncoding("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			Throwables.propagate(e);
		}
		return template;
	}

	public static String initPath() {
        try {
            if(StringUtils.isNotBlank(path)) return path;
            path = FtlUtil.class.getClassLoader().getResource("").getPath();
            path = path + source_folder;
            path = URLDecoder.decode(path, "utf-8") + File.separator;
            if ("\\".equals(File.separator)) {
                path = path.substring(1);
                path = path.replace("/", "\\");
            }
        } catch (Exception e) {
            Throwables.propagate(e);
            logger.error("初始化模板路径信息错误",e);
            e.printStackTrace();
        }
        return path;
    }

	public static void main(String args[]) throws IOException, TemplateException {
		String s = FtlUtil.initPath();
		System.out.println(s);
		// System.out.println(ftl2String("quiz.ftl", null));
	}

}
