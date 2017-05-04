package com.dmwys.photography.startup;
import com.dmwys.photography.cacheCenter.IocContext;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;


public class MvcSetup implements Setup {
	@Override
	public void init(NutConfig config) {
		Ioc ioc = Mvcs.getIoc();
        IocContext.setIoc(ioc);
	}
	@Override
	public void destroy(NutConfig config) {
	}
}
