<%@ page contentType="text/html;charset=GBK" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link type="text/css" rel="stylesheet" href="css/common.css" />
	<link type="text/css" rel="stylesheet" href="css/content.css" />
	<title>404页面</title>
</head>

<body>
<div class="error_cont">
	<div class="error_bg fl"></div>
	<div class="error_txt">
    	<h2>
        	<i class="error_type t404"></i>
        	<span>错误</span>
        </h2>
        <div class="error_info">
        	<p><a href="javascript:history.back();">返回上一页</a>  <a href="getApplyMain.do" target="mainFrame">返回首页</a></p>
        	<h3>对不起，您访问的页面不存在</h3>
            <ul>
            	<li>请检查您访问的请求地址是否正确。</li>
                <li>感谢您的使用，我们会尽快解决此问题。</li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>