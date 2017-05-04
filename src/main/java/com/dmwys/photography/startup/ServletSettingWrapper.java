package com.dmwys.photography.startup;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.dmwys.photography.util.PropertyHelper;

public class ServletSettingWrapper extends HttpServletRequestWrapper {
	public ServletSettingWrapper(HttpServletRequest request) {
	    super(request);
        PropertyHelper.AddKeysAndValues(request);
	}

}
