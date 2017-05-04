
$("#_area_choose_content").on('click','.checkTrueTerminal', function() {
    var checkboxArr = $(this).parents("li").find(".TerminalSearch").find("input[type=checkbox]"),_self=$(this);
        checkboxArr.each(function(i,item){
        if(_self.prop("checked")){
            $(item).prop("checked",true).parent().addClass("current");
        }else{
            $(item).prop("checked",false).parent().removeClass("current");
        }
    });
});

//checkbox  选中 取消
$("#_area_choose_content").on('click','input[type="checkbox"]', function() {
	if ($(this).get(0).checked) {
		//子复选框 全选
		if ($(this).hasClass("childAllChoose")) {
			$(this).parent().addClass("current");
			$(this).closest('tr').siblings().find('div').addClass("current");
			var childInput = $(this).closest('tr').siblings().find('input');
			for (var i = 0; i < childInput.length; i++) {
				childInput[i].checked = true;
			}
		}
		//终端全选	
		else if ($(this).hasClass("allChoose")) {
			$(this).parent().addClass("current");
			$(this).closest('.allTerminal').siblings().find('.TerminalName').addClass("current");
			$(this).closest('.allTerminal').siblings().find('.typeChoose').removeAttr('disabled', 'disabled');
			var trueinput = $(this).closest('.allTerminal').siblings().find('.TerminalName').find('input');
			for (var i = 0; i < trueinput.length; i++) {
				trueinput[i].checked = true;
			}
		}
		//普通全选
		else {
			if ($(this).hasClass("checkTrueTerminal")) {
				$(this).parent().siblings().find('.inputReadonly').removeAttr('disabled');
			}
			$(this).parent().addClass("current");
			$(this).parent().parent().find('.contentinput').removeAttr('disabled');

		}

	} else {
		//子复选框 全选
		if ($(this).hasClass("childAllChoose")) {
			$(this).parent().removeClass("current");
			$(this).closest('tr').siblings().find('div').removeClass("current");
			var childInput = $(this).closest('tr').siblings().find('input');
			for (var i = 0; i < childInput.length; i++) {
				childInput[i].checked = false;
			}
		}
		//终端 全 不选	
		if ($(this).hasClass("allChoose")) {
			$(this).parent().removeClass("current");
			$(this).closest('.allTerminal').siblings().find('.TerminalName').removeClass("current");
			$(this).closest('.allTerminal').siblings().find('.typeChoose').attr('disabled', 'disabled');
			var falseinput = $(this).closest('.allTerminal').siblings().find('.TerminalName').find('input');
			for (var i = 0; i < falseinput.length; i++) {
				falseinput[i].checked = false;
			}
		}
		//普通 不选
		else {
			if ($(this).hasClass("checkTrueTerminal")) {
				$(this).parent().siblings().find('.inputReadonly').attr('disabled', 'disabled');
			}
			$(this).parent().removeClass("current");
			$(this).parent().parent().find('.contentinput').attr('disabled', 'true');
		}
	}
});



//终端显示隐藏
$("#_area_choose_content").on("click",".inputReadonly",function(){
    $(this).closest('.everyTerminal').siblings().find('.tableBoxDiv').hide();
    $(this).siblings('.tableBoxDiv').show();

});

$("#_area_choose_content").on("click",".tableSureButton",function(){
    $(this).closest('.tableBoxDiv').hide();
});

function initAreaInfo(ctx,cardId,cardType){
	$.ajax({
		url: ctx + "/getAreaInfo?cardId="+cardId+"&cardType="+cardType,
		type: "post",
		dataType: "json",
		context: document.body,
		success: function(txt){
			if(txt.code == 0){
				var compiled = _.template(document.getElementById('_area_choose').innerHTML);
				document.getElementById('_area_choose_content').innerHTML = compiled({data:txt.data.areaInfo});
			}else{
				$("#_area_choose_content").html(txt.desc);
			}
		}

	});
}
