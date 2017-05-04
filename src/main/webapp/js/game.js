//终端 地区 用户 切换
var show_choose_li = $('.show_choose_ul .show_choose_li');
var TerminalAreaUser = $('.show_choose_content');

show_choose_li.on('click', function() {
	$(this).css('background', '#eeb831').siblings().css('background', '#c0c5c9');
	TerminalAreaUser.css('display', 'none');
	TerminalAreaUser.eq($(this).index()).css('display', 'block');
});




//选择创建节目框
function chooseColumnInfo(){
	var url = "chooseColumnsPage?target=data_column_info";
	openwin(url);
}
function chooseMutiProductInfo(){
    var url = "chooseMutiProductInfo?target=data_program_join_list";
    openwin(url);
}
function checkBoxCallback(Wopenee,target,arrID,arrName) {
    init(target,arrID,arrName);
}
function init(target,arrID,arrName){
    for(var i in arrID){
        var html = '<span class="program_span_box">' +
            '<input type="hidden" name="productId" value='+arrID[i]+' />' +
            '<input type="hidden" name="productName" value='+arrName[i]+ ' />' +
            arrName[i] + '<span class="removeProgramBox">X</span></span>';
        $("." + target).append(html);
    }
}
function initProductIDs(target,pIDStrs,PNameStrs){
    var pIDStrs = (pIDStrs ||'').split(",");
    var pNameStrs = (PNameStrs ||'').split(",");
    var PIDs = new Array();
    var PNames = new Array();
    for(var index = 0 ; index < pIDStrs.length ; index ++) {
        if (!!pIDStrs[index]) {
            PIDs.push(pIDStrs[index]);
            PNames.push(pNameStrs[index]);
        }
    }
    init(target,PIDs,PNames);
}

//栏目选择回调方法
function callBack(Wopenee,target){
	var radiovalue = "";
	var radioname = "";
	//获取所有单选框
	var radioObj = Wopenee.document.getElementsByName("radio");
	for (i = 0; i < radioObj.length; i++) {
		//获取选中的值
		if (radioObj[i].checked) {
			radiovalue = radioObj[i].value;
			radioname = radioObj[i].getAttribute("data");
		}
	}
	if(!!radiovalue){
	    $("#data_column_info").show();
        //关闭当前窗口
        $("#"+target).find(".data_columnId").val(radiovalue);

        $("#"+target).find(".data_columnName").val(radioname);
        $("#"+target).find(".data_show_column_name").html(radioname);
    }
	Wopenee.close();
}





//删除节目框
$('.program_listHead').on('click', '.removeProgramBox', function() {
    $("#data_column_info").find(".data_columnId").val("");
    console.log($(this).parent(".data_columnId"));
    $("#data_column_info").find(".data_columnName").val("");
    $("#data_column_info").find(".data_show_column_name").html("");
    $("#data_column_info").hide();

});
$('.program_listJoin').on('click', '.removeProgramBox', function() {
	$(this).parent('.program_span_box').hide();

});




//任务分类  每项切换
$('.taskClassSelect').on('change', function() {
    initTaskType();
});
function initTaskType(){
    for (var i = 0; i < $(".taskClassSelect").find('option').length; i++) {
        if ($(".taskClassSelect").get(0).selectedIndex == i) {
            $('.votesNumber').eq(i).css('display', 'block').siblings('.votesNumber').css('display', 'none');
            $('.votesNumber').eq(i).find(":input").prop("disabled",false);
            $('.votesNumber').eq(i).siblings('.votesNumber').find(":input").val("").prop('disabled', true);
        }
    }
}


function initReword(hasReward){
    if(!!hasReward){
        $('.extraBox').css("display","block");
    }
}
//额外奖励有无
$('.extraSelect').on('change', function() {
	if ($(this).get(0).selectedIndex == 0) {
		$('.extraBox').css('display', 'none');
        $('.extraBox').find(":input").prop("disabled",true).val("");
	} else {
		$('.extraBox').css('display', 'block');
        $('.extraBox').find(":input").prop("disabled",false);
	}
});


//标题 展示 隐藏
var showHideJoin = true; //参与条件
$('.jion_condition_head_button').on('click', function() {
	if (showHideJoin) {
		$(this).parent().siblings('.jion_condition_sumTime').slideDown(300);
		showHideJoin = false;
	} else {
		$(this).parent().siblings('.jion_condition_sumTime').slideUp(300);
		showHideJoin = true;
	}
});

var showHideCondition = true; //任务说明
$('.task_instruction_head_button').on('click', function() {
	if (showHideCondition) {
		$('.task_instruction_body').slideDown(300);
		showHideCondition = false;
	} else {
		$('.task_instruction_body').slideUp(300);
		showHideCondition = true;
	}
});
var showHideCardConnect = true; //卡片互联
$('.card_connect_head_button').on('click', function() {
	if (showHideCardConnect) {
		$('#card_connect .card_connect_ul').slideDown(300);
		showHideCardConnect = false;
	} else {
		$('#card_connect .card_connect_ul').slideUp(300);
		showHideCardConnect = true;
	}
});
var showHideCCard = true; //C面互联
$('.C_card_connect_button').on('click', function() {
	if (showHideCCard) {
		$('#C_card_connect .C_card_connect_body').slideDown(300);
		showHideCCard = false;
	} else {
		$('#C_card_connect .C_card_connect_body').slideUp(300);
		showHideCCard = true;
	}
});
var showHideShowChoose = true; //展示选择
$('.show_choose_button').on('click', function() {
	if (showHideShowChoose) {
		$('.showChooseBody').slideDown(300);
		showHideShowChoose = false;
	} else {
		$('.showChooseBody').slideUp(300);
		showHideShowChoose = true;
	}
});


//产品选择和取消选择
$("#_terminal_choose_content").on('click','.checkTrueTerminal', function() {
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
$("#_terminal_choose_content").on('click','input[type="checkbox"]', function() {
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
$("#_terminal_choose_content").on("click",".inputReadonly",function(){
    $(this).closest('.everyTerminal').siblings().find('.tableBoxDiv').hide();
    $(this).siblings('.tableBoxDiv').show();

});
//终端选择联动 ：如果选择了机型，将改产品也勾选上
function terminalLinkage(){
    $(".everyTerminal").each(function(){
        if($(this).find(".tableBoxDiv input[type=checkbox]:not(:first()):checked").length){
            $(this).find(".TerminalName input").prop("checked",true).parent().addClass('current');
            $(this).find(".inputReadonly").removeAttr("disabled").removeAttr("readonly");
        }
    })

    // $(".TerminalSearch").each(function(){
    //
    //
    //     if($(this).parent().find('input[type=checkbox]:checked').length > 0){
    //         $(this).parent().find(".checkTrueTerminal").prop("checked",true);
    //         $(this).find(".checkTrueTerminal").parent("div").addClass("current");
    //         $(this).find(".inputReadonly").removeAttr("readonly").removeAttr("disabled")
    //     }else if($(this).find(".checkTrueTerminal").prop("checked") == false){
    //         $(this).find(".checkTrueTerminal").parent("div").removeClass("current");
    //     }
    //
    // });
}

$("#_terminal_choose_content").on("click",".tableSureButton",function(){
    $(this).closest('.tableBoxDiv').hide();
});


function initProductInfo(ctx,cardId,cardType){
    $.ajax({
        url: ctx + "/getProductInfo?cardId="+cardId+"&cardType="+cardType,
        type: "post",
        dataType: "json",
        context: document.body,
        success: function(txt){
            if(txt.code == 0){
                var compiled = _.template(document.getElementById('_terminal_choose').innerHTML);
                document.getElementById('_terminal_choose_content').innerHTML = compiled({data:txt.data.productInfo});
                terminalLinkage();
            }else{
                $("#_terminal_choose_content").html(txt.desc);
            }
        }

    });
}

