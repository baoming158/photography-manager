<#-- 通用错误模板  -->
<#import "../global.ftl" as global/>
{
    "result-code": "-1",
    "version": "${global.APIVersion}",
	"cmd": "${cmd!''}",
    "msg": "${result}",
    "timestamp": "${timeStamp?string("yyyy-MM-dd HH:mm:ss")}",
    "success":false,
    "data": {
    }
}