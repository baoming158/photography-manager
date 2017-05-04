<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%> 
<script language="javascript">
function submit(){
	var maxPage="${pm.maxPage}";
	var value=document.getElementById("currentPage").value;
 	if(parseInt(value)>0 && (parseInt(value)<parseInt(maxPage )|| parseInt(value)==parseInt(maxPage))){
	console.log(maxPage + "       " + value);
		window.location="<%=request.getContextPath()%>/${pm.pageUrl}?currentPage="+value+"${pm.queryString}";
	}
}
</script>
<c:if test="${pm.result!=null}">
	<li><a href="${ctx}/${pm.pageUrl}?currentPage=1${fn:escapeXml(pm.queryString)}">首页</a></li>
	
	<c:if test="${pm.pageNo == 1}">
		<li class="prev disabled"><a href="#" class="table-arrow"><i class="entypo-left-open"></i></a></li>
	</c:if>
	<c:if test="${pm.pageNo != 1}">
		<li class="prev"><a href="${ctx}/${pm.pageUrl}?currentPage=${pm.pageNo-1}${fn:escapeXml(pm.queryString)}" class="table-arrow"><i class="entypo-left-open"></i></a></li>
	</c:if>
	
	
	<!-- 加左侧页码1 -->
	<c:if test="${pm.pageNo > pm.viewPage}">
		<c:forEach var="index" begin="${pm.pageNo-pm.viewPage}" end="${pm.viewPage+pm.pageNo}">  
			<c:if test="${index == pm.pageNo}">
				<li class="active"><a href="${ctx}/${pm.pageUrl}?currentPage=${index}${fn:escapeXml(pm.queryString)}">${index}</a></li>
			</c:if>
			<c:if test="${index != pm.pageNo && (index<pm.maxPage || index==pm.maxPage)}">
		  		<li><a href="${ctx}/${pm.pageUrl}?currentPage=${index}${fn:escapeXml(pm.queryString)}">${index}</a></li>
		  	</c:if>
		</c:forEach>
	</c:if>
	<!-- 加左侧页码2-->
	<c:if test="${(pm.pageNo>1) && (pm.pageNo < pm.viewPage || pm.pageNo == pm.viewPage)}">
		<c:forEach var="index" begin="1" end="${pm.pageNo}">  
			<c:if test="${index != pm.pageNo && (index<pm.maxPage || index==pm.maxPage)}">
		  		<li><a href="${ctx}/${pm.pageUrl}?currentPage=${index}${fn:escapeXml(pm.queryString)}">${index}</a></li>
		  	</c:if>
		</c:forEach>
	</c:if>
	
	<!-- 加右侧页码1 -->
	<c:if test="${(pm.pageNo < pm.viewPage || pm.pageNo == pm.viewPage) && (pm.maxPage>(pm.viewPage+pm.pageNo) || pm.maxPage==(pm.viewPage+pm.pageNo))}">
		<c:forEach var="index" begin="${pm.pageNo}" end="${pm.viewPage+pm.pageNo}">  
			<c:if test="${index == pm.pageNo}">
				<li class="active"><a href="${ctx}/${pm.pageUrl}?currentPage=${index}${fn:escapeXml(pm.queryString)}">${index}</a></li>
			</c:if>
			<c:if test="${index != pm.pageNo && (index<pm.maxPage || index==pm.maxPage)}">
		  		<li><a href="${ctx}/${pm.pageUrl}?currentPage=${index}${fn:escapeXml(pm.queryString)}">${index}</a></li>
		  	</c:if>
		</c:forEach>
	</c:if>
	<!-- 加右侧页码2 -->
	<c:if test="${(pm.pageNo < pm.viewPage || pm.pageNo == pm.viewPage) && (pm.maxPage<(pm.viewPage+pm.pageNo))}">
		<c:forEach var="index" begin="${pm.pageNo}" end="${pm.maxPage}">  
			<c:if test="${index == pm.pageNo}">
				<li class="active"><a href="${ctx}/${pm.pageUrl}?currentPage=${index}${fn:escapeXml(pm.queryString)}">${index}</a></li>
			</c:if>
			<c:if test="${index != pm.pageNo && (index<pm.maxPage || index==pm.maxPage)}">
		  		<li><a href="${ctx}/${pm.pageUrl}?currentPage=${index}${fn:escapeXml(pm.queryString)}">${index}</a></li>
		  	</c:if>
		</c:forEach>
	</c:if>
	
	
	<c:if test="${pm.pageNo >= pm.maxPage}">
		<li class="next disabled"><a href="#" class="table-arrow"><i class="entypo-right-open"></i></a></li>
	</c:if>
	<c:if test="${pm.pageNo < pm.maxPage}">
		<li class="next"><a href="${ctx}/${pm.pageUrl}?currentPage=${pm.pageNo+1}${fn:escapeXml(pm.queryString)}" class="table-arrow"><i class="entypo-right-open"></i></a></li>
	</c:if>
	
	<li><a href="${ctx}/${pm.pageUrl}?currentPage=${pm.maxPage }${fn:escapeXml(pm.queryString)}">尾页</a></li>
	
	<li class="next"><input type="text" id="currentPage" name="currentPage" value="" style="float:left;height:29px;width:70px;margin:auto 5px;" class="form-control" placeholder="共${pm.maxPage }页"/></li>
	<li class="next1"><a href="#" class="btn btn-default" onClick="submit()">跳转</a></li>	
</c:if>