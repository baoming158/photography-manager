<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%> 
<%@include file="/common/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Neon Admin Panel" />
	<meta name="author" content="" />
	
	<title>卡片管理系统</title>
	
	<link rel="stylesheet" href="<%=basePath%>/assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/css/font-icons/entypo/css/entypo.css">
	<!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">-->
	<link rel="stylesheet" href="<%=basePath%>/assets/css/bootstrap.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/css/neon-core.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/css/neon-theme.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/css/neon-forms.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/css/custom.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/css/ky.css">

	<script src="<%=basePath%>/assets/js/jquery-1.11.0.min.js"></script>

	<!--[if lt IE 9]><script src="<%=basePath%>/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	
</head>
<script type="text/javascript">
$(document).ready(function() {
		var msg = "${message}"||"";
 		if(!!msg){
			$("#tips").html(msg);
		}
	});
	function redirect(){
		var url ="${url}"||"";

		if(!!url){
			window.location.href="<%=basePath%>"+url;
		}else{
			if(navigator.userAgent.toUpperCase().indexOf("CHROME") === -1){
				history.back();
			}
			else {
				try{
					history.go(-1);
				}catch(e){
					history.go(-1);
				}
				return false;
			}
		}
	}
</script>
<body>
	<div id="tips"></div>
	<a href="#" class="backBtn" onclick="javascript:redirect();">返回</a>
</body>
</html>

