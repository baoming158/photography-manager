<#import "../global.ftl" as global/>
{
    "result-code": "${result_code!'-1'}",
    "version": "${global.APIVersion}",
	"cmd": "${cmd}",
    "msg": "${result}",
    "timestamp": "${timeStamp?string("yyyy-MM-dd HH:mm:ss")}",
    "data": {
	        "data_list": 
	        	<#if data_list??>
	        			{
							    "label":"${data_list.label!''}",
								"sub_label":[
									<#if data_list.lable_names??>
										<#list data_list.lable_names as sub>
											"${sub}"<#if sub_has_next>,</#if>
										</#list>
									</#if>
								]
								
	                    }
	            </#if>
  		}
	}