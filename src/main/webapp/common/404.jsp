<%@ page contentType="text/html;charset=GBK" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setStatus(200);%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link type="text/css" rel="stylesheet" href="css/common.css" />
	<link type="text/css" rel="stylesheet" href="css/content.css" />
	<title>404ҳ��</title>
</head>

<body>
<div class="error_cont">
	<div class="error_bg fl"></div>
	<div class="error_txt">
    	<h2>
        	<i class="error_type t404"></i>
        	<span>����</span>
        </h2>
        <div class="error_info">
        	<p><a href="javascript:history.back();">������һҳ</a>  <a href="getApplyMain.do" target="mainFrame">������ҳ</a></p>
        	<h3>�Բ��������ʵ�ҳ�治����</h3>
            <ul>
            	<li>���������ʵ������ַ�Ƿ���ȷ��</li>
                <li>��л����ʹ�ã����ǻᾡ���������⡣</li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>