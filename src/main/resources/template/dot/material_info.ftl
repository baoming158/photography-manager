<#import "../global.ftl" as global/>
{
    "result-code": "${result_code!'-1'}",
    "version": "${global.APIVersion}",
	"cmd": "${cmd}",
    "msg": "${result}",
    "timestamp": "${timeStamp?string("yyyy-MM-dd HH:mm:ss")}",
    "data": {
	        "data_list": 
	        			{
	        			    "id":"${data_list.material_id?c}",
	        			    "name":"${data_list.material_name!''}",
							"dot_start_time":"${data_list.dot_start_time!''}",
		        			"dot_end_time":"${data_list.dot_end_time!''}",
		        			"material_start_time":"${data_list.material_start_time!''}",
		        			"material_end_time":"${data_list.material_end_time!''}",
		        			"allowable_error_senconds":"${data_list.allowable_error_senconds!''}",
							"show_seconds":"${data_list.show_seconds!''}",
							"type":"${data_list.type?c}",
							"img_url":"${data_list.img_url!''}",
							"position":"${data_list.position?c}",
							"position_val":"${data_list.position_val}",
							"label_names":[
								<#if data_list.lable_names??>
									<#list data_list.lable_names as label>
								{
									"sub_label":[
										<#if label.sub_label??>
											<#list label.sub_label as sub>
												"${sub}"<#if sub_has_next>,</#if>
											</#list>
										</#if>
									]
								}<#if label_has_next>,</#if>
									</#list>
								</#if>
							]
	                    }
  		}
	}