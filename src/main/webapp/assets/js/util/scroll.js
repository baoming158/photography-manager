
// 滚动字幕的标题和内容文字控制
	var oCtrlText = getElsByClass(document, 'ky_text_ctrl'), // 所有前景色、背景色控制元素集合
		ky_ty_tits = getElsByClass(document, 'ky_ty_tit'), 	 // 所有滚动字幕中的标题元素集合
		ky_ty_conts = getElsByClass(document, 'ky_ty_cont');  // 所有滚动字幕中的内容元素集合

	for(var i = 0, len = oCtrlText.length; i < len; i++) {
		var aOpts = oCtrlText[i].children,
			tit_forColor = aOpts[1].children[0],     // 标题文字前景色 
			tit_bgColor = aOpts[3].children[0],	     // 标题文字背景色
			cont_forColor = aOpts[5].children[0],    // 内容文字前景色
			cont_bgColor = aOpts[7].children[0];     // 内容文字背景色

		(function (index) {
			// 滚动字幕 - 设置标题文字前景色
			tit_forColor.onblur = function () {
				forColor(this, ky_ty_tits[index], '#555');
			}

			// 滚动字幕 - 设置标题文字背景色
			tit_bgColor.onblur = function () {
				bgColorfn(this, ky_ty_tits[index], '#fff');
			}

			// 滚动字幕 - 设置内容文字前景色
			cont_forColor.onblur = function () {
				forColor(this, ky_ty_conts[index], '#555');
			}

			// 滚动字幕 - 设置内容文字背景色
			cont_bgColor.onblur = function () {
				bgColorfn(this, ky_ty_conts[index], '#fff');
			}
		})(i);
	}