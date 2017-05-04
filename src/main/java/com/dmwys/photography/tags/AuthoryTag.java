package com.dmwys.photography.tags;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.nutz.mvc.Mvcs;

public class AuthoryTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String value;
	@Override
	public int doStartTag() throws JspException {
		boolean hasAuth = AuthUtil.hasAuth(Mvcs.getHttpSession(),value);
		if(hasAuth){
			return EVAL_BODY_INCLUDE;
		}else{
			return SKIP_BODY;
		}
	}

	@Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;

	}

	@Override
	public void release() {
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
