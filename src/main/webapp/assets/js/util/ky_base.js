(function () {

	// 校验字段
	var forms = getElsByClass(document, 'ky_form'), // 页面中所有 form 集合
		subBtn = getElsByClass(document, 'ky_submit'); // 页面中所有 submit 集合
 
	for(var i = 0, len = forms.length; i < len; i++) {
		var coll = getElsByClass(forms[i], 'ky_required');
	}
	
	
})();