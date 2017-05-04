<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Neon Admin Panel" />
<meta name="author" content="" />

<title>编辑打点标签</title>

<link rel="stylesheet" type="text/css" media="screen"
	href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">

<link rel="stylesheet"
	href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
<link rel="stylesheet"
	href="assets/css/font-icons/entypo/css/entypo.css">
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/css/neon-core.css">
<link rel="stylesheet" href="assets/css/neon-theme.css">
<link rel="stylesheet" href="assets/css/neon-forms.css">
<link rel="stylesheet" href="assets/css/custom.css">
<link rel="stylesheet" href="assets/css/ky_common.css">
<script src="assets/js/util/publicJS.js"></script>
<script src="assets/js/jquery-1.11.0.min.js"></script>

<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->

<style type="text/css">
.ky_t_control {
    width: 191px;
    height: 30px;
    line-height: 30px;
    padding-left: 39px;
}
.new_div{
	
}
</style>

</head>
<body class="page-body">
	<div class="main-content ky_fs12">
		<ol class="breadcrumb bc-3">
			<li><a href="index.jsp"><i class="entypo-home"></i>首页</a></li>
			<li class="active"><strong>标签编辑</strong></li>
		</ol>
		<br>
		<div class="col-md-12">
			<div class="panel panel-primary">
				<form action="${ctx}/editDotLabel" method="post">
					<input type="hidden" name="id" id="id" value="${model.id }" />
					<input type="hidden" name="parentId" id="parentId" value="${model.parentId }" />
						<div class="row ky_mb20">
							<div class="col-md-1 ky_t_control">名称</div>
							<div class=col-md-2>
								<!-- 跳转时标志为点击为创建的页面 -->
								<input type="text" class="form-control ky_title_w ky_ty_tit"
									id="name" name="name" placeholder="名称"
									value="${model.name }">
							</div>
						</div>
						<div class="row ky_mb20">
							<div class="row">
								<div class="col-md-6"></div>
								<div class="col-md-6">
									<button type="submit" class="btn btn-blue"
										style="padding-left: 20px; padding-right: 20px; ">保存</button>
								</div>
							</div>
						</div>
				</form>
			</div>
		</div>
	</div>
	<script src="assets/js/main-gsap.js"></script>
	<script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/joinable.js"></script>
	<script src="assets/js/resizeable.js"></script>
	<script src="assets/js/neon-api.js"></script>
	<script src="assets/js/neon-custom.js"></script>
	<script src="assets/js/neon-demo.js"></script>
	<script src="assets/js/util/publicJS.js"></script>
	<script src="assets/js/util/all_select_term.js"></script>
	<script src="assets/js/util/scroll.js"></script>


	<script src="assets/js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>
