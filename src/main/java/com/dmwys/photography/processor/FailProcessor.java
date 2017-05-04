package com.dmwys.photography.processor;

import java.util.HashMap;
import java.util.Map;

import com.dmwys.photography.util.FtlUtil;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionInfo;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.View;
import org.nutz.mvc.impl.processor.ViewProcessor;
import org.nutz.mvc.view.RawView;
import org.nutz.mvc.view.ViewWrapper;

import com.dmwys.photography.cacheCenter.CacheCenter;

import freemarker.template.Template;

public class FailProcessor extends ViewProcessor {

    private static final Log log = Logs.get();

    @Override
    public void init(NutConfig config, ActionInfo ai) throws Throwable {
         view = evalView(config, ai, ai.getFailView());
    }

    public void process(ActionContext ac) throws Throwable {
        if (log.isWarnEnabled()) {
            String uri = Mvcs.getRequestPath(ac.getRequest());
            log.warn(String.format("Error@%s :", uri), ac.getError());
        }
        Object re = ac.getMethodReturn();
        Object err = ac.getError();
        if (re != null && re instanceof View) {
            if (re instanceof ViewWrapper)
                putRequestAttribute(ac.getRequest(), ((ViewWrapper)re).getData());
            ((View) re).render(ac.getRequest(), ac.getResponse(), err);
        } else {//在这儿定义错误跳转
            putRequestAttribute(ac.getRequest(), null == re ? err : re);
            if(err instanceof Exception){
            	Template template = CacheCenter.FtlCache.get("common/error");
            	Map<String,Object> map = new HashMap<String,Object>();
            	map.put("result", ((Exception) err).getMessage());
            	if(map.get("result") == null){
            		map.put("result", "系统错误");
            	}
            	map.put("result_code", -1);
            	view = new ViewWrapper(new RawView(null), FtlUtil.ftl2String(template,map,ac.getRequest()));
            }
            view.render(ac.getRequest(), ac.getResponse(), null == re ? err : re);
        }
        doNext(ac);
    }
}
