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
	        			    "dot_id":"${data.id!''}",
		        			"start_time":"${data.start_time_offset_milliseconds}",
							"end_time":"${data.end_time_offset_milliseconds}",
							"lable_names":[
								<#if data.lable_names??>
									<#list data.lable_names  as label>
										{
											"label":"${label.label!''}"
											<#if label.sub_label??>
											,"sub_label":[
													<#list label.sub_label as sub>
														"${sub!''}"<#if sub_has_next>,</#if>
													</#list>
											]
											</#if>
										}<#if label_has_next>,</#if>
									</#list>
								</#if>
							],
							"operator_id":"${data.modify_id?c!''}",
							"operator_name":"${data.modify_name}",
							"operator_time":"${data.modify_time}"
	                    }<#if data_has_next>,</#if>
	                 </#list>
	        	</#if>
	        ]
  		}
	}