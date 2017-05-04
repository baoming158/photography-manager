<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
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
	
	<script language="javascript">
		var nn = 5;
		function num(){
	 		document.getElementById("nuber").innerHTML = nn;
	 		nn --;
	 		if(nn==0){
				parent.location.href="${ctx}/login.jsp"
			}
		}
		function SetNum(){
		    setInterval("num()",1000);
		}
		
		function redirect(){
			parent.location.href="${ctx}/login.jsp";
			
		}
	</script>
</head>

<body onload="SetNum();">
	<div id="tips">由于长时间未操作或操作不规范，session失效了,<span id="nuber">5</span>&nbsp;&nbsp;秒后<a href="#" onclick="javascript:redirect();"><span style="color:red;">返回登录页面</span></a></div>
</body>
</html>