<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="Neon Admin Panel" />
<meta name="author" content="" />

<title>选择产品</title>


<link rel="stylesheet"
	href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
<link rel="stylesheet"
	href="assets/css/font-icons/entypo/css/entypo.css">
<!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">-->
<link rel="stylesheet" href="assets/css/bootstrap.css">
<link rel="stylesheet" href="assets/css/neon-core.css">
<link rel="stylesheet" href="assets/css/neon-theme.css">
<link rel="stylesheet" href="assets/css/neon-forms.css">
<link rel="stylesheet" href="assets/css/custom.css">
<link rel="stylesheet" href="assets/css/ky.css">

<script src="assets/js/jquery-1.11.0.min.js"></script>

<!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body class="page-body" data-url="http://neon.dev">

	<div class="page-container sidebar-collapsed pop-page">
		<!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->

		<div class="main-content pop-content">

			<div class="row">
				<div class="col-md-12">

					<div class="panel panel-primary" data-collapsed="0">

						<div class="panel-heading">
							<div class="panel-title">选择产品</div>
							<div class="panel-options" style="padding-top: 5px;">
								<button type="submit" class="btn btn-default"
									onclick="getCheckedNodes('${target}')">选择</button>
							</div>
						</div>
					</div>
				<div class="panel-body">
				
				<form role="form" method="post" action="${ctx}/chooseMutiProductInfo?target=${target}" class="form-horizontal form-groups-bordered">
					<div class="form-group">
						<div class="col-sm-2">
							<input type="text" class="form-control" id="productName" name="productName" value="${productName}" placeholder="产品名称">
						</div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-default">搜索</button>
						</div>
					</div>
				</form>
			</div>
				</div>
			</div>
			<div id="table-2_wrapper"
				class="dataTables_wrapper form-inline pop-table" role="grid">
				<table
					class="table table-bordered table-striped datatable table-font"
					id="table-2">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							<th>产品名称</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach var="model" items="${productList}">
							<tr>
								<td>
									<div class="checkbox checkbox-replace">
										<input type="checkbox" class="data_checkbox" name="id" value="${model.id}" data="${model.product_name}"/>
									</div>
								</td>
								<td>${model.id}</td>
								<td>${model.product_name}</td>
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
							</ul>
						</div>
					</div>
				</div>

			</div>
			<br />
		</div>
	</div>

	<link rel="stylesheet"
		href="assets/js/datatables/responsive/css/datatables.responsive.css">
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
	<!--<script src="assets/js/datatables/jquery.dataTables.columnFilter.js"></script>-->
	<!--<script src="assets/js/datatables/lodash.min.js"></script>-->
	<script
		src="assets/js/datatables/responsive/js/datatables.responsive.js"></script>
	<!--<script src="assets/js/select2/select2.min.js"></script>-->
	<!--<script src="assets/js/neon-chat.js"></script>-->
	<script src="assets/js/neon-custom.js"></script>
	<!--<script src="assets/js/neon-demo.js"></script>-->

</body>
<script>
	function getCheckedNodes(target){
		var arrID =new Array();
		var arrName =new Array();
		var radioObj = $("input[type=checkbox]:checked");
		for (i = 0; i < radioObj.length; i++) {
			//获取选中的值
			if (radioObj[i].checked) {
				arrID.push(radioObj[i].value);
				arrName.push(radioObj[i].getAttribute("data"));
			}
		}
        window.opener.checkBoxCallback(window,target,arrID,arrName);

		window.close();
	}

</script>
</html>
