<%@ page contentType="text/html;charset=GBK" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page import="com.dmwys.photography.log.*" %>--%>
<%response.setStatus(200);%>
<%--<%--%>
	<%--//记录日志--%>
	<%--Throwable ex = null;--%>
	<%--ex = (Throwable) request.getAttribute("obj");--%>
	<%--CardsLog.ERROR.error("错误页面",ex);--%>
<%--%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Neon Admin Panel" />
	<meta name="author" content="" />
	
	<title>卡片管理系统</title>
	
	<link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css"/>
	<link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css"/>
	<!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">-->
	<link rel="stylesheet" href="assets/css/bootstrap.css"/>
	<link rel="stylesheet" href="assets/css/neon-core.css"/>
	<link rel="stylesheet" href="assets/css/neon-theme.css"/>
	<link rel="stylesheet" href="assets/css/neon-forms.css"/>
	<link rel="stylesheet" href="assets/css/custom.css"/>
	<link rel="stylesheet" href="assets/css/ky.css"/>

	<script src="assets/js/jquery-1.11.0.min.js"></script>

	<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	
</head>

<body>
	<input type="hidden" value="${obj } "/> 
	<div id="tips">系统错误，请确认您的操作是否正确或联系管理员</div>
	<a href="javascript:history.back();" class="backBtn">返回</a>
</body>
</html>