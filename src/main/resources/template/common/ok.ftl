<#-- 通用成功模板  -->
<#import "../global.ftl" as global/>
{
    "result-code": "0",
    "version": "${global.APIVersion}",
	"cmd": "${cmd}",
    "msg": "OK",
    "timestamp": "${timeStamp?string("yyyy-MM-dd HH:mm:ss")}",
    "success":true,
    "data": {
    }
}