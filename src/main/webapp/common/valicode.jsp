<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%> 
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Neon Admin Panel" />
	<meta name="author" content="" />
	
	<title>卡片管理系统</title>

	<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	<%
		String obj = request.getParameter("obj");
	 	request.setAttribute("setVal",obj);
	 	String form = request.getParameter("form");
	 	request.setAttribute("form",form);
	 	String id = request.getParameter("id");
	 	request.setAttribute("id",id);
	 %>
</head>

<body>
	<div class="container" style="width:310px;margin:0 auto;">
		<div>
			<div class="text" style="height:50px;line-height:50px;font-size:14px;">为了保证数据安全，请推送前正确输入验证码</div>
			<div class="valicode" style="padding:10px 0;height:30px;">
				<input type="text" name="check_code" id="check_code" style="float:left;font-size:20px;padding:2px;width:80px;font-weight:bold;text-align:center;"/>
	        	<img onclick="flushIMG(this);" title="点击刷新验证码！" src="chineseVal.jsp" style="float:left;width:110px;height:30px;margin-left:20px;"/>
			</div>
	    </div>
	    <div>
	    	<c:if test="${not empty form }">
		    	<input type="submit" class="st_mouseout" value="确　认" onclick="javascipt:getValue();" style="margin-top:20px;border-radius:5px;height:30px;width:80px;font-size:12px;font-weight:bold;text-align:center;"/>        
	    	</c:if>
	    	<c:if test="${ empty form }">
		    	<input type="submit" class="st_mouseout" value="确　认" onclick="javascipt:getValue_ajax();" style="margin-top:20px;border-radius:5px;height:30px;width:80px;font-size:12px;font-weight:bold;text-align:center;"/>        
	    	</c:if>
	    </div>
	 </div>
</body>
	<script src="${ctx }/assets/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	var XMLHttpReq = null;
	function flushIMG(obj){
		var time = new Date().getTime();
		obj.src="chineseVal.jsp?i="+time;
	}
	//表单提交时使用方法
	function getValue(){
		var code =  document.getElementById('check_code').value;
		window.opener.document.getElementById("${setVal}").value = code;
		if(!code){
			alert('验证码不能为空');
			return ;
		}
		window.opener.document.getElementById('${form}').submit();
		window.close();
	}
	//ajax提交时使用方法
	function getValue_ajax(){
		var code =  document.getElementById('check_code').value;
		if(!code){
			alert('验证码不能为空');
			return ;
		}
		var id= '${id}'||'';
		var url = "${ctx}/pushSubscribeAjax?id="+id+"&validate_code="+code;
		$.ajax({
			type:"post",
			url: "${ctx}/pushSubscribeAjax?id="+id+"&validate_code="+code,
			async:false,
			dataType: "json",
			beforeSend: function(){
			},
			success: function(txt){
				if(txt.result == 0){
					alert(txt.desc);
				}else{
					alert(txt.desc);
				}
	 		},
	 		complete: function(){
	 			window.close();
	 		}
	 		
		});
	}
	document.onkeydown=function(event){
		e = event ? event :(window.event ? window.event : null);
		if(e.keyCode==13){
			if(!!'${form}'){
				getValue();
			}else{
				getValue_ajax();
			}
		}
	} 
	window.onload = function(){
		document.getElementById('check_code').focus();
	}
</script>
</html>

