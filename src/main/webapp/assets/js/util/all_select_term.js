(function () {
	
	
		// 所有终端列表
		var termList = document.getElementsByClassName('term_list');

		// 所有选中的终端显示区域
		var viewSelTerm = document.getElementsByClassName('view_selected_term');

		// 所有的 "全部" 选择项
		var allSelIterms = document.getElementsByClassName('m_all_term');

		addEventInput();
	

	// 为每个终端项绑定 onclick 事件
	function addEventInput() {
		for(var i = 0; i < termList.length; i++) {
			// 每个列表中的所有终端项
			var termListItems = termList[i].getElementsByClassName('term_item');
			// 每个列表中的"全部"选择项
			// var mAllIterms = termList[i].getElementsByClassName('m_all_term')[0];
			var mAllIterms = allSelIterms[i];
			// 每个列表中的确定
			var btnOk = termList[i].getElementsByClassName('term_ok')[0];
			// 每个列表中的取消
			var btnCancel = termList[i].getElementsByClassName('term_cancel')[0];
			
			termListItems.index = i;
			btnOk.index = i;
			btnCancel.index = i;
			mAllIterms.index = i;
			
			// 确定
			btnOk.onclick = function () {
				var index = this.index;
				// 每个列表中的所有终端项
				var termListItems = termList[index].getElementsByClassName('term_item');
				
				clickOk(termListItems, viewSelTerm[index]);
			};
			// 取消
			(function () {
				btnCancel.onclick = function () {
					var index = this.index;
					console.log(index);
					// 每个列表中的所有终端项
					var termListItems = termList[index].getElementsByClassName('term_item');
					// 每个列表中的"全部"选择项
					//var mAllIterms = termList[index].getElementsByClassName('m_all_term')[0];
					
					clickCancel(termListItems, mAllIterms, viewSelTerm[index]);
				}
			})(mAllIterms);
			
			// "全部"
			mAllIterms.onclick = function () {
				var index = this.index;
				// 每个列表中的所有终端项
				var termListItems = termList[index].getElementsByClassName('term_item');
				for(var m = 0; m < termListItems.length; m++) {
					termListItems[m].checked = !!this.checked;
				}
			};
			
			(function () {
				var temp = termListItems;
				var mTemp = mAllIterms;
				// 每个列表中的终端项绑定 onclick 事件
				for(var q = 0, len = temp.length; q < len; q++) {
					// 每个列表项
					temp[q].onclick = function () {
						inputClickFn(temp, mTemp);
					};
				}
			})(termListItems,mAllIterms);
		}
	}

	// currEl     当前终端项
	// terms      所有终端项集合
	// allTermEl  "全部"项
	function inputClickFn(terms, allTermEl) {
		var n = 0;
		for(var j = 0, len = terms.length; j < len; j++) {
			// 统计有多少终端选中
			terms[j].checked == true && n++;
			// 如果都选中,"全部"项选中,否则取消选中
			allTermEl.checked = n === len ? true : false;
		}
	}

	// 点击确定
	function clickOk(termListEl,viewSelTerm) {
		var oFrag = document.createDocumentFragment();
		for(var j = 0, len = termListEl.length; j < len; j++) {
			if(termListEl[j].checked === true) {
				oFrag.appendChild(createEl('span', 'kj_term',termListEl[j].nextSibling.nodeValue));
			}
		}
		viewSelTerm.innerHTML = '';
		// 将选中的终端添加到显示区域
		viewSelTerm.appendChild(oFrag);
	}

	// 点击取消
	function clickCancel(termListEl,mAllTerm,viewSelTerm) {
		for(var j = 0, len = termListEl.length; j < len; j++) {
			termListEl[j].checked = false;
		}
		mAllTerm.checked = false;
		// 在显示区域去掉选中的终端
		viewSelTerm.innerHTML = '';
	}

	// 生成 HTML 
	function createEl(tagName, className, inner) {
		var oEl = document.createElement(tagName);
		oEl.className = className;
		oEl.innerHTML = inner;
		return oEl;
	}
	
	// 初始化 
	function init() {
		for(var z = 0; z < termList.length; z++) {
			var temp = termList[z].getElementsByClassName('term_item');
			clickOk(temp,viewSelTerm[z]);
		}
	}
	
	init();
	
})();