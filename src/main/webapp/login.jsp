<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%> 
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Neon Admin Panel" />
	<meta name="author" content="" />
	
	<title>摄像后台管理系统</title>
	

	<link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
	<link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css">
	<!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">-->
	<link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/css/neon-core.css">
	<link rel="stylesheet" href="assets/css/neon-theme.css">
	<link rel="stylesheet" href="assets/css/neon-forms.css">
	<link rel="stylesheet" href="assets/css/custom.css">

	<script src="assets/js/jquery-1.11.0.min.js"></script>

	<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	
	
</head>
<body class="page-body login-page login-form-fall" data-url="http://neon.dev">


<!-- This is needed when you send requests via Ajax --><script type="text/javascript">
var baseurl = '';
</script>

<div class="login-container">
	
	<div class="login-header login-caret">
		
		<div class="login-content">
			<img src="assets/images/logo@2x.png" width="120" alt="" />
			
			<!--<p class="description">Dear user, log in to access the admin area!</p>-->
			
			<!-- progress bar indicator -->
			<div class="login-progressbar-indicator">
				<h3> 43%</h3>
				<span>登录中...</span>
			</div>
		</div>
		
	</div>
	
	<div class="login-progressbar">
		<div></div>
	</div>
	
	<div class="login-form">
		
		<div class="login-content">
			
			<div class="form-login-error">
				<p>Enter <strong>demo</strong>/<strong>demo</strong> as login and password.</p>
			</div>
			
			<form method="post" role="form" id="form_login" >
				
				<div class="form-group">
					
					<div class="input-group">
						<div class="input-group-addon">
							<i class="entypo-user"></i>
						</div>
						
						<input type="text" class="form-control" name="username" id="username" placeholder="用户名" autocomplete="off" />
					</div>
					
				</div>
				
				<div class="form-group">
					
					<div class="input-group">
						<div class="input-group-addon">
							<i class="entypo-key"></i>
						</div>
						
						<input type="password" class="form-control" name="password" id="password" placeholder="密码" autocomplete="off" />
					</div>
				
				</div>
				<!-- <div class="form-group">
					
					<div class="input-group">
						<div class="input-group-addon">
							<i class="entypo-mobile"></i>
						</div>
						
						<input type="text" class="form-control" name="phonenum" id="phonenum" placeholder="验证码" autocomplete="off" style="width:150px;">
						<input type="button" value="获取验证码" id="phonebtn" style="float: left;margin-left: 10px;    background-color: rgb(85, 99, 128);    border-radius: 5px;    font: 15px/27px 'Helvetica Neue', '黑体';    color: #fff;    cursor: pointer;    width: 100px;cursor: pointer;border: none;">
					</div>
				
				</div> -->
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block btn-login">
						<i class="entypo-login"></i>
						登录
					</button>
				</div>
			</form>
			
			
			<!--<div class="login-bottom-links">
				
				<a href="extra-forgot-password.html" class="link">Forgot your password?</a>
				
				<br />
				
				<a href="#">ToS</a>  - <a href="#">Privacy Policy</a>
				
			</div>-->
			
		</div>
		
	</div>
	
</div>


	<!-- Bottom Scripts -->
	<script src="assets/js/gsap/main-gsap.js"></script>
	<script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/joinable.js"></script>
	<script src="assets/js/resizeable.js"></script>
	<script src="assets/js/neon-api.js"></script>
	<script src="assets/js/jquery.validate.min.js"></script>
	<script src="assets/js/neon-login.js"></script>
	<script src="assets/js/neon-custom.js"></script>
	<script src="assets/js/neon-demo.js"></script>
	<script>
	    $('#phonebtn').click(function () {
	        var username=document.getElementById("username").value;
	        sendXHR(username);
	    });
		 function sendXHR(username) { 
			$.ajax({
				type: "post", //AJAX请求类型  
				url: "sendServlet?username="+username, //请求url  
				cache: false,  //无缓存  
				timeout: 1000 * 140,  //AJAX请求超时时间为120秒  
				data: { time: 120}, //参数time时间为最多等待（后台保持）时间(120秒无论是否有数据立即返回)，单位为秒。 
				success: function (data, textStatus) {
					if(null!=data && ""!=data){
						$('.form-login-error p').html(data);
						$(".login-page").removeClass('logging-in');
						neonLogin.resetProgressBar(true);
					}else{
						$('.form-login-error p').html("");
						$('.form-login-error').hide();
					 var $this = $('#phonebtn')
		                , count = 120
		                , intervalId;
				        intervalId=setInterval(function(){
				            $this.attr('disabled','disabled');
				            $this.css('cursor','default')
				            $this.val(count--+'s后重试');
				            if(count<=0){
				                clearInterval(intervalId);
				                count=120;
				                $this.removeAttr("disabled");
				                $this.val('获取验证码');
				                $this.css('cursor','pointer')
				            }
				        },1000);
					}
				},  
				error: function (XMLHttpRequest, textStatus, errorThrown) {  
		        }  
			});  
		} 
	</script>

</body>
</html>
