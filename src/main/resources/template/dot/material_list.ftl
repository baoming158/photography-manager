<#import "../global.ftl" as global/>
{
    "result-code": "${result_code!'-1'}",
    "version": "${global.APIVersion}",
	"cmd": "${cmd}",
    "msg": "${result}",
    "timestamp": "${timeStamp?string("yyyy-MM-dd HH:mm:ss")}",
    "data": {
	        "data_list": [
	        	<#if data_list??>
	        		<#list data_list as data>
	        			{
	        				"id":"${data.id}",
							"name":"${data.name}",
							"status":"${data.status?c!''}",
		        			"dot_start_time":"${data.dot_start_time!''}",
		        			"dot_end_time":"${data.dot_end_time!''}",
		        			"material_start_time":"${data.start_time_offset_milliseconds!''}",
		        			"material_end_time":"${data.end_time_offset_milliseconds!''}",
							"show_seconds":"${data.show_seconds!''}",
							"type":"${data.type?c}",
							"img_url":"${data.img_url!''}",
							"position":"${data.position?c}",
							"position_val":"${data.position_val}",
							"modify_name":"${data.modify_name}",
							"modify_time":"${data.modify_time}"
	                    }<#if data_has_next>,</#if>
	                 </#list>
	        	</#if>
	        ]
  		}
	}