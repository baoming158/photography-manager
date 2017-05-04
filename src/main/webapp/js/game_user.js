
//page切换
function pageList(num, current,viewSize) {
	$(".pageDiv").createPage({
		pageCount: num, //总页数
		current: current, //当前页
		turndown: 'true', //是否显示跳转框，显示为true，不现实为false,一定记得加上引号...
		backFn: function(p) {
			pageShow(p,viewSize);
		}
	});
}
function pageShow(p,viewSize){
	$("._user_data_tables").hide();
	var arr = $("._user_data_tables").slice(parseInt(p-1)* parseInt(viewSize) ,parseInt(p)*parseInt(viewSize));
	arr.each(function(index,item){
		$(item).show();
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
				var userSize = _.size(txt.data.data);
                var pageView = 10;
				var pageSize = parseInt(userSize/pageView) + (userSize%pageView == 0 ? 0 : 1);
                pageList(parseInt(pageSize) ,1,pageView);
				pageShow(1,pageView);
			}else{
				$("#_area_choose_content").html(txt.desc);
			}
		},
        error:function(txt){
		    console.log(txt);
		}
	});
}
