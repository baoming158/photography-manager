package com.dmwys.photography.framework.page;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.dmwys.photography.util.Constants;

/**
 * 分页模型
 */
public class PageModel<T> {
	private Map<String,Object> argsmap = new HashMap<String,Object>();
	/**
	 * 查询数据结果集
	 */
	private List<T> result;
	/**
	 * 总页码数
	 */
	private int maxPage;
	/**
	 * 每页显示多少条
	 */
	private int pageSize = 10;
	/**
	 * 实际显示页码数
	 */
	private int viewPage;
	/**
	 * 总记录数
	 */
	private int count;
	/**
	 * 当前页码
	 */
	private int pageNo;
	/**
	 * 分页请求链接
	 */
	private String pageUrl;
	/**
	 * 请求参数
	 */
	private String queryString;
	public PageModel() {
		super();
	}
//	public PageModel(List<T> result, int maxPage,int viewPage,int count,int pageNo,String pageUrl) {
//		super();
//		this.result = result;
//		this.maxPage = count / pageSize + (count % pageSize == 0 ? 0 : 1 );
//		this.viewPage = viewPage;
//		this.count = count;
//		this.pageNo = pageNo;
//		this.pageUrl = pageUrl;
//	}
	public PageModel(HttpServletRequest request,List<T> result,int count) {
		this(request,result,count, Constants.PAGE_SIZE,2);
	}
	@SuppressWarnings("unchecked")
	public PageModel(HttpServletRequest request,List<T> result,int count,int pageSize,int viewPage) {
		this.pageSize = pageSize;
		this.result = result;
		this.maxPage = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		this.viewPage = viewPage;
		this.pageUrl = request.getServletPath().substring(1);//   "showXXX.do"
		this.count=count;
		Enumeration<String> querynames = request.getParameterNames();
		while(querynames.hasMoreElements()){
			String key = null;
			String value = request.getParameter(key = querynames.nextElement());
			argsmap.put(key, value);
		}
		String currentPage = request.getParameter("currentPage") ;
		if("-1".equals(currentPage) || "null".equals(currentPage)){
			currentPage="1";
		}
		pageNo = currentPage == null ? 1 : Integer.parseInt(currentPage);
		argsmap.put("currentPage",pageNo);
		argsmap.put("count",count);
		
		this.queryString = argsFactory(argsmap);
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public int getViewPage() {
		return viewPage;
	}

	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
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
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
