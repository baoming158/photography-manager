<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Neon Admin Panel" />
	<meta name="author" content="" />

	<title>产品管理</title>

	<script src="assets/js/jquery-1.11.0.min.js"></script>
	<link rel="stylesheet" href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
	<link rel="stylesheet" href="assets/css/font-icons/entypo/css/entypo.css">
	<!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">-->
	<link rel="stylesheet" href="assets/css/bootstrap.css">
	<link rel="stylesheet" href="assets/css/neon-core.css">
	<link rel="stylesheet" href="assets/css/neon-theme.css">
	<%--<link rel="stylesheet" href="assets/css/custom.css">--%>
	<%--<link rel="stylesheet" href="assets/css/neon-forms.css">--%>
	<%--<link rel="stylesheet" href="assets/css/ky.css">--%>
	<link rel="stylesheet" href="assets/js/select2/select2-bootstrap.css">
	<link rel="stylesheet" href="assets/js/select2/select2.css">

	<!--[if lt IE 9]>
	<script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<style type="text/css">
		.row{margin-top: 5px;}
		.select2-choice{height: 33px;}
	</style>

</head>
<body class="page-body">
<ol class="breadcrumb bc-3">
	<li><a href="index.jsp"><i class="entypo-home"></i>首页</a></li>
	<li class="active"><strong>产品管理</strong></li>
</ol>
<br>

<div class="row">
	<div class="col-md-12">

		<div class="panel panel-primary" data-collapsed="0">

			<div class="panel-body">
				<form role="form" method="post" name="form" id="form"
					  action="${ctx}/showProduct"
					  class="form-horizontal form-groups-bordered">
					<input type="hidden" id="validate" name="validate" value="" />
					<div class="row">
						<label class="col-sm-1 control-label">产品名称：</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="context" name="context"
								   value="${context}" placeholder="产品名称">
						</div>
						<div class="col-sm-2">
							<div class="col-sm-2">
								<button type="submit" class="btn btn-default validate-btn">搜索</button>
							</div>
						</div>
					</div>
				</form>

			</div>

		</div>

	</div>
</div>
<div class="row ky_mb20">
	<div class="col-md-12">
	</div>
</div>
<div class="col-md-12">
	<div class="panel-group joined" id="accordion-test-2">
		<div id="table-2_wrapper" class="dataTables_wrapper form-inline"
			 role="grid">
			<table width="100%"
				   class="table table-bordered table-striped datatable table-font"
				   style="table-layout: fixed;" id="table-2">
				<thead>
				<tr style="word-wrap: break-word;">
					<th style="width: 10%">产品ID</th>
					<th style="width: 20%">产品内容</th>
					<th style="width: 20%">海报图</th>
					<th style="width: 20%">修改时间</th>
					<th style="width: 10%">操作人</th>
					<th style="width: 10%">操作</th>
				</tr>
				</thead>

				<tbody>
				<c:forEach var="model" items="${pm.result}">
					<tr>
						<td>${model.id}</td>
						<td>${model.context}</td>
						<td><img width="110px" height="100px" src="${model.img}"/></td>
						<td><fmt:formatDate value="${model.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</td>
						<td>${model.name}</td>
						<td width="400px">
							<a href="web_page/index.html?id=${model.id}"

							   class="btn btn-info icon-left pdl10"> 编辑
							</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class="row">
				<div class="col-xs-6 col-left">
					<div class="dataTables_info" id="table-2_info"></div>
				</div>
				<div class="col-xs-6 col-right">
					<div class="dataTables_paginate paging_bootstrap">
						<ul class="pagination pagination-sm">
							<%@include file="/common/pager.jsp"%>
						</ul>
					</div>
				</div>
			</div>

		</div>

		<br />
	</div>

</div>



<link rel="stylesheet" href="assets/js/datatables/responsive/css/datatables.responsive.css">
<!--<link rel="stylesheet" href="assets/js/select2/select2-bootstrap.css">
<link rel="stylesheet" href="assets/js/select2/select2.css">-->

<!-- Bottom Scripts -->
<script src="assets/js/gsap/main-gsap.js"></script>
<!--<script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>-->
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/joinable.js"></script>
<script src="assets/js/resizeable.js"></script>
<script src="assets/js/neon-api.js"></script>
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/datatables/TableTools.min.js"></script>
<script src="assets/js/dataTables.bootstrap.js"></script>
<script src="assets/js/neon-custom.js"></script>
<script src="assets/js/bootstrap-tagsinput.min.js"></script>

<script src="assets/js/select2/select2.min.js"></script>

</body>
</html>
