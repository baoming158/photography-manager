var ioc = {
    methodAop : {
            type :'com.dmwys.photography.aop.MethodInteceptor'
    },
    apiAop : {
    	type :'com.dmwys.photography.aop.APIInteceptor'
    },
    $aop : {
        type : 'org.nutz.ioc.aop.config.impl.ComboAopConfigration',
        fields : {
                aopConfigrations  : [
                        {       type : 'org.nutz.ioc.aop.config.impl.JsonAopConfigration',
                                fields : {
                                itemList : [
                                            ['com\\.dmwys\\.photography\\..+','.+','ioc:methodAop']
                                ]
                                }
                        },
                        {       type : 'org.nutz.ioc.aop.config.impl.AnnotationAopConfigration'}
                ]
        }
    }
};