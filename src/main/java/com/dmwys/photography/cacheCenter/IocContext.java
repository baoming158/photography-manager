package com.dmwys.photography.cacheCenter;

import org.nutz.ioc.Ioc;

public class IocContext {
	public static Ioc ioc ;
	
	public static Ioc getIoc() {
		return ioc;
	}

	public static void setIoc(Ioc ioc) {
		if(ioc != null){
			IocContext.ioc = ioc;
		}
	}
	public static void init(Ioc ioc){
		setIoc(ioc);
	}
	
}
