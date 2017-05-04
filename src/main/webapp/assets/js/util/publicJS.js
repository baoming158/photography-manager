/**
 * 设置标题、内容文字前景色
 * obj    - 标题或内容前景色值元素
 * taregt - 标题或内容元素
 * type   - css 属性值
 * value  - 默认前景色
*/
function forColor(obj, target, value) {
	if(target.value !== '') {
		// 如果前景色至不为空且颜色值为 #xxx 或 #xxxxx 格式
		// /(^#[a-fA-F0-9]{6}$)|(^#[a-fA-F0-9]{3}$)/
		if(obj.value !== '' && /(^#[a-fA-F0-9]{6}$)/.test(obj.value)) {
				target.style.color = obj.value;
		}
		else if(obj.value === ''){
			target.style.color = value; // 颜色值为空设置默认颜色
		}
		else { 
			alert('颜色值有误！请重新输入！格式为： #xxxxxx');
			obj.value = '';
			obj.focus();
		}
	}
	else {
		// alert('文字为空，请输入文字！');
		target.focus();
	}	
}

/**
 * 设置标题、内容文字背景色
 * obj    - 标题或内容背景色值元素
 * taregt - 标题或内容元素
 * type   - css 属性值
 * value  - 默认背景色
*/
function bgColorfn(obj, target, value) {
	if(obj.value !== '' && /(^#[a-fA-F0-9]{8}$)/.test(obj.value)) {
		target.style.backgroundColor = tranRgba(obj.value);
	}
	else if(obj.value === '') {
		target.style.backgroundColor = tranRgba(value); // 颜色值为空设置默认颜色
	}
	else {
		alert('颜色值有误！请重新输入！格式为： #xxxxxxxx');
		obj.value = '';
		obj.focus();
	}
} 

// 兼容的 getElementsByClassName
function getElsByClass(obj, cName) {
	var obj = obj || document;
	if(obj.getElementsByClassName) return obj.getElementsByClassName(cName);

	var nodes = obj.getElementsByTagName('*'),
		reg = new RegExp("(^|\\s)"+cName+"(\\s|$)"),
		result = [];

	for(var i = 0, len = nodes.length; i < len; i++) {
		if(reg.test(nodes[i].className)) {
			result.push(nodes[i]);
		}
	}

	return result;
}

/**
 * 将 8 位的 16 进制颜色值转换为 rgba 模式   
 * str    {string} 16 进制颜色值
 * return {string} rgba 模式的颜色值 
 * e.g. #bbccaa08 => rgba(187, 204, 170, 0.8);
 **/
function tranRgba(str) {
	return 'rgba(' + parseInt(str.substring(1, 3), 16) + ', ' 
		+ parseInt(str.substring(3, 5), 16) + ', ' 
		+ parseInt(str.substring(5, 7), 16) + ', ' 
		+ (+str.substring(7) <= 10 && str.charAt(7) !== '' ? str.substring(7) / 10 : 1) + ')';
}