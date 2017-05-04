(function () {
	// 删除已创建的列表
	var kyCreatedList = getElsByClass(document, 'kyCreatedList'); // 已创建的拉片列表集合

	for(var i = 0, len = kyCreatedList.length; i < len; i++) {
		var kyDel = getElsByClass(kyCreatedList[i], 'kyDel')[0];
		(function (index) {
			kyDel.flag = false;
			kyDel.onclick = function () {
				var con_re = confirm('确定要删除嘛?');
				if(con_re) {
					kyCreatedList[index].style.display = 'none';
				}
			}
		})(i);
	}

	// 推荐列表删除
	var kyRecomList = getElsByClass(document, 'kyRecomList');

	for(var i = 0, len = kyRecomList.length; i < len; i++) {
		var kyRecom = kyRecomList[i].lastElementChild,
			kyRecomDel = kyRecom.children[1];

		(function (index) {
			kyRecomDel.flag = false;
			kyRecomDel.onclick = function () {
				var con_re = confirm('确定要删除嘛?');
				if(con_re) {
					kyRecomList[index].style.display = 'none';
				}
			}
		})(i);
	}
})();