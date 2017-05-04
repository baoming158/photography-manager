<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/authtaglib" prefix="s" %>
<c:set var="ctx" value="${base}"/>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", -10);
%>
<script LANGUAGE="javascript">
    //打开新窗口
    function openwin(action) {
        window.open(action, "newwindow", "height=570,width=790,top=50,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
    }
    //选择数据时调用此方法
    function selchgRadio(setname, clear) {
        var radiovalue = "";
        var radioname = "";
        //获取所有单选框
        var radioObj = document.getElementsByName("radio");
        for (i = 0; i < radioObj.length; i++) {
            //获取选中的值
            if (radioObj[i].checked) {
                radiovalue = radioObj[i].value;

                radioname = radioObj[i].getAttribute("data");
            }
        }
        //给父窗口设置值
        if (setname.indexOf(",") > -1) {
            if (clear) {
                var clears = clear.split(",");
                for (var i = 0; i < clears.length; i++) {
                	alert(clears[i]);
                    window.opener.document.getElementById(clears[i]).value = null;
                }
            }


            var arr = setname.split(",");
            window.opener.document.getElementById(arr[0]).value = radiovalue;
            window.opener.document.getElementById(arr[1]).value = radioname;


        } else {
            window.opener.document.getElementById(setname).value = radiovalue;
            var err = $(window.opener.document.getElementById(setname)).removeClass('input-invalidate').siblings('.input-error');
            err.html('');
        }

        //关闭当前窗口
        window.close();
    }
    function clearImg(id) {
        $("#" + id).val("");
    }
    function deleteItem(url, msg) {
        msg = msg || '确定要删除吗？';
        var flag = confirm(msg);
        if (flag) {
            window.location.href = "${ctx}/" + url;
        }
    }
    function validateCode(obj, form) {
        window.open("common/valicode.jsp?obj=" + obj + "&form=" + form, "newwindow", "height=570,width=790,top=50,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
    }
    function editMachineTypeFn(card_id, card_type, url) {
        window.location.href = "${base}/initCardsMachineType?card_id=" + card_id + "&card_type=" + card_type + "&url=" + url;
    }
    function editCardUserInfoFn(card_id, card_type, url) {
        window.location.href = "${base}/initCardsUserInfo?card_id=" + card_id + "&card_type=" + card_type + "&url=" + url;
    }
    function editProviceCityInfoFn(card_id, card_type, url) {
        window.location.href = "${base}/initProviceCityInfo?card_id=" + card_id + "&card_type=" + card_type + "&url=" + url;
    }
    function editChannelInfoFn(card_id, card_type, url) {
        window.location.href = "${base}/initChannelInfo?card_id=" + card_id + "&card_type=" + card_type + "&url=" + url;
    }
    function editCardConnectionInfoFn(card_id, card_type, url) {
        window.location.href = "${base}/initConnectionSingle?card_id=" + card_id + "&card_type=" + card_type + "&url=" + url;
    }
    function editProductTypeFn(card_id, card_type, url) {
        window.location.href = "${base}/initCardsProduct?card_id=" + card_id + "&card_type=" + card_type + "&url=" + url;
    }
    function editAreasInfoFn(card_id, card_type, url) {
        window.location.href = "${base}/initAreasInfo?card_id=" + card_id + "&card_type=" + card_type + "&url=" + url;
    }
    window.onload = function () {
        //控制测试人员是否能选择终端
        var terminal = "${sessionScope.terminal}" || "";
        if (terminal == 1) {
            $('.judgeReadonly input').attr('disabled', 'disabled');
        }


        //var arrJudge = $('.judgeReadonly');

        //if(!arrJudge) {
        //	return ;
        //}
        //if(${sessionScope.user.name == 'xinjiang' or sessionScope.user.name == 'wangxiaojian'}){
        //	for(var i = 0, len = arrJudge.length; i < len; i++) {
        //		var child = arrJudge[i].children[0].children[0].children[0].innerHTML.split('<')[0];
        //		if(child.trim() !== '新疆广电') {
        //			arrJudge[i].style.display = 'none';
        //		}
        //	}
        //}

    };
    
    function tijiao(){
    	var flag = '1';
    	$("#base_option_group_container .option_group").not(":first").find("input[type=text]").each(
        		function(){
        			var val = $(this).val();
        			if(val==null || val ==''){
        				alert("请正确输入选项值");
        				flag='2';
        			}
        		}		
        	); 
    	if(flag =='2'){
    		return false;
    	}
        var vlist="";
    	$("input[type|='text']").each(function(){
    	    var value = $(this).val(); //这里的value就是每一个input的value值~
    	    vlist=vlist+value;
    	    
    	});
    	$("textarea").each(function(){
    	    var value = $(this).text(); //这里的value就是每一个input的value值~
    	    vlist=vlist+value;
    	});
    	
    	vlist = vlist.replace("#","");
	    censorWord(vlist, "censor_word", "SensorCallback");
    }
    
    function createScript(src, id,fn) {
        id = id || 'js_jsonp_2015';
        var el = document.createElement('script');
        var oScript = document.getElementById(id);
        el.src = src;
        el.type = "text/javascript";
        el.id = id;
        oScript && document.body.removeChild(oScript);
        document.body.appendChild(el);
        //fn()
    }

    function SensorCallback(res) {
        if (res.code == 0) {
            var data = res.forbid_word_list;
            if (data.length > 0) {
                alert("包含敏感词：" + data.join(","));
            } else{
            	$("#form").submit();
            }
        }
    }

    function censorWord(str, id, fn) {
    	var actionurl = $("#form").attr('action');
    	var url ="http://172.21.62.101:7766/word_censor/api";
        var wordUrl = url + "?word=" + encodeURIComponent(str) + "&source=cards&url="+actionurl+"&userId=${user.id}&userName=${user.name}&callback=" + fn;
        console.log(wordUrl);
        createScript(wordUrl, id,fn);
    }
</script>