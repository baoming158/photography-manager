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
							"url":"http://testdianbo.kuyun.com/dianbo/video/${data_list.aid!''}/${data_list.id!''}.mp4",
							"aid":"${data_list.aid?c}",
							"id":"${data_list.id?c}",
		        			"senconds":"${data_list.ltime!''}",
		        			"begin":"${data_list.begin!''}",
		        			"end":"${data_list.end!''}"
	                    }
  		}
	}