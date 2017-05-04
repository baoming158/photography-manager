package com.dmwys.photography.startup;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.UrlMappingBy;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
@UrlMappingBy(value=MyUrlMapImpl.class)
//@ChainBy(type=NutActionChainMaker.class,args={"ioc/mvc-chains.js"})
@Modules(scanPackage = true,packages="com.dmwys.photography")
@IocBy(type = ComboIocProvider.class,args = {"*org.nutz.ioc.loader.json.JsonLoader",
		"ioc",
        "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", 
        "com.dmwys.photography"
 })
@SetupBy(MvcSetup.class) 
@Filters({@By(type=SessionFilter.class),@By(type=AuthorityFilter.class)})
@Encoding(input = "UTF-8", output = "UTF-8")
@Fail("json")
public class MainModule {
}
