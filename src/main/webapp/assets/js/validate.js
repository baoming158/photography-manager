//表单提交方法
$('.validate-btn').bind('click',function(){
	var $err=$('.input-invalidate');
	if($err.length){
		return;
	}else{
		//获得要验证的组件名
		var validate=document.getElementById( "validate" );
		var form = document.getElementById( "form" );
		if(!validate || !validate.value || validate.value == ""){
			form.submit();
		}else{
			var values=validate.value.split(',');
			for(var i=0;i<values.length;i++){
				var tag=document.getElementById(values[i]);
				if(! tag.value || tag.value == ""){
					var err = $(tag).addClass('input-invalidate').siblings('.input-error');
					err.html('不能为空！');
					return;
				}
			}
			form.submit();
		}
	}
});
//判断名称不能为空
function checkName( tag ){
	if( ! tag.value || tag.value == "" ){
		var err = $(tag).addClass('input-invalidate').siblings('.input-error');
		err.html('不能为空！');
	}else{
		var err = $(tag).removeClass('input-invalidate').siblings('.input-error');
		err.html('');
	}
}
//判断是否数字并不能为空
function numberNotNull(tag){
	if( ! tag.value || tag.value == "" ){
		var err = $(tag).addClass('input-invalidate').siblings('.input-error');
		err.html('不能为空！');
	}else{
		if (isNaN(tag.value)) { //不是数字
			var err = $(tag).addClass('input-invalidate').siblings('.input-error');
			err.html('必须是数字！');
		}else{//是数字
			var err = $(tag).removeClass('input-invalidate').siblings('.input-error');
			err.html('');
		}
	}
}
//判断是否数字
function isNumberOr_Letter(tag){
	if (isNaN(tag.value)) { //不是数字
		var err = $(tag).addClass('input-invalidate').siblings('.input-error');
		err.html('必须是数字！');
	}else{//是数字
		var err = $(tag).removeClass('input-invalidate').siblings('.input-error');
		err.html('');
	}
}