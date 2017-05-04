
//page切换
pageList(18, 5); //共18页，当前第5页
function pageList(num, current) {
	$(".pageDiv").createPage({
		pageCount: num, //总页数
		current: current, //当前页
		turndown: 'true', //是否显示跳转框，显示为true，不现实为false,一定记得加上引号...
		backFn: function(p) {
			//// 单击回调方法，p是当前页码

		}
	});
}

function initUserInfo(ctx,cardId,cardType){
	$.ajax({
		url: ctx + "/getUserGroupInfo?cardId="+cardId+"&cardType="+cardType,
		type: "post",
		dataType: "json",
		context: document.body,
		success: function(txt){
			if(txt.code == 0){
				var compiled = _.template(document.getElementById('_user_choose').innerHTML);
				document.getElementById('_user_choose_content').innerHTML = compiled({data:txt.data.data});
			}else{
				$("#_area_choose_content").html(txt.desc);
			}
		}

	});
}
