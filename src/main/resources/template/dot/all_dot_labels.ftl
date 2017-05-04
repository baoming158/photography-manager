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
							"name":"${data.name!''}"
	                    }<#if data_has_next>,</#if>
	                 </#list>
	        	</#if>
	        ]
  		}
	}