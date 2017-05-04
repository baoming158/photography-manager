(function () {
	// 通用卡片前景色背景色控制
	var kyUseCtrl = getElsByClass(document, 'kyUseCtrl'),  // 通用卡片 - 所有前景色、背景色控制元素集合
		kyUseTits = getElsByClass(document, 'kyUseTit'),   // 通用卡片 - 所有标题集合
		kyUseConts = getElsByClass(document, 'kyUseCont'); // 通用卡片 - 所有内容集合

	for(var i = 0, len = kyUseCtrl.length; i < len; i++) {
		var kyUseCtrlOpts = getElsByClass(kyUseCtrl[i], 'kyUseCtrlOpt'), 
			kyUseTit_forColor = kyUseCtrlOpts[0],  // 标题文字前景色
			kyUseTit_bgColor = kyUseCtrlOpts[1],   // 标题文字背景色
			kyUseCont_forColor = kyUseCtrlOpts[2], // 内容文字前景色
			kyUseCont_bgColor = kyUseCtrlOpts[3];  // 内容文字背景色

		(function (index) {
			// 通用卡片 - 设置标题前景色
			kyUseTit_forColor.onblur = function () {
				forColor(this, kyUseTits[index], '#555');
			}

			// 通用卡片 - 设置内容前景色
			kyUseCont_forColor.onblur = function () {
				forColor(this, kyUseConts[index], '#555');
			}

			// 通用卡片 - 设置标题背景色
			kyUseTit_bgColor.onblur = function () {
				bgColorfn(this, kyUseTits[index], '#fff');
			}

			// 通用卡片 - 设置内容背景色
			kyUseCont_bgColor.onblur = function () {
				bgColorfn(this, kyUseConts[index], '#fff');
			}
		})(i);
	}

	// 通用卡片 - 是否循环触发
	var kyUseLoop = getElsByClass(document, 'kyUseLoop'),
		kyUseEveryDays = getElsByClass(document, 'kyUseEveryDay'), // 每天同一时间集合
		kyUseDiys = getElsByClass(document, 'kyUseDiy'), 		   // 自定义集合	
		kyUseFirsts = getElsByClass(document, 'kyUseFirst'), 	   // 首次触发隔多少分钟后再次触发集合
		kyUseLoopNums = getElsByClass(document, 'kyUseLoopNum');   // 循环次数集合

		function yesFn(index) {
			// 每天同一时间禁用并清除选中
			kyUseEveryDays[index].disabled = '';
			kyUseEveryDays[index].checked = '';

			// 自定义禁用并清除选中
			kyUseDiys[index].disabled = '';
			kyUseDiys[index].checked = '';

			// 首次触发隔多少分钟后再次触发禁用
			kyUseFirsts[index].disabled = '';
			
			// 循环次数
			kyUseLoopNums[index].disabled = '';
		}

		function noFn(index) {
			// 每天同一时间禁用并清除选中
			kyUseEveryDays[index].disabled = 'disabled';
			kyUseEveryDays[index].checked = '';

			// 自定义禁用并清除选中
			kyUseDiys[index].disabled = 'disabled';
			kyUseDiys[index].checked = '';

			// 首次触发隔多少分钟后再次触发禁用
			kyUseFirsts[index].disabled = 'disabled';
			kyUseFirsts[index].value = '';

			// 循环次数
			kyUseLoopNums[index].disabled = 'disabled';
			kyUseLoopNums[index].value = '';
		}

		for(var i = 0, len = kyUseLoop.length; i < len; i++) {
			var aInputs = kyUseLoop[i].getElementsByTagName('input');
			
			// 点击是
			(function (index) {
				aInputs[0].onclick = function () {
					yesFn(index);
				}
			})(i);

			// 点击否
			(function (index) {
				aInputs[1].onclick = function () {
					noFn(index);
				};		
			})(i);

			document.onkeyup = function (e) {
				var ev = e || event;
				if(ev.keyCode === 8) {
					return false;
				}
			}
		}


	// 手动触发和智能触发事件
	var ky_handTriggers = getElsByClass(document,'ky_handTrigger'),
		trigger_time = getElsByClass(document, 'Wdate'); // 日历
//	console.log(ky_handTriggers[0].getElementsByTagName('input')[1]);

	for(var i = 0, len = ky_handTriggers.length; i < len; i++) {
		var handTrigger = ky_handTriggers[i].getElementsByTagName('input')[0], // 手动触发按钮
			intellTrigger = ky_handTriggers[i].getElementsByTagName('input')[1], // 智能触发按钮
			aInput = kyUseLoop[i].getElementsByTagName('input'); // 是否循环触发 是、否
			triTime = trigger_time[i]; // 日历

		(function (index) {
			// 手动触发
			handTrigger.onclick = function () {
				triTime.disabled = 'disabled';
				aInput[0].disabled = aInput[1].disabled = 'disabled';
				noFn(index);
			};
			// 智能触发
			intellTrigger.onclick =function () {
				triTime.disabled = '';
				aInput[0].disabled = aInput[1].disabled = '';
				yesFn(index);
			};
		})(i);
	}


	// 每天同一时间和自定义
	var everydays = getElsByClass(document, 'kyUseEveryDay'),
		diys = getElsByClass(document, 'kyUseDiyInput');

	for(var i = 0; i < everydays.length; i++) {
		(function (index) {
			everydays[index].onclick = function () {
				diys[index].disabled = 'disabled';
			}

			diys[index].onclick = function () {
				this.disabled = '';
			}
		})(i);
	}

})();