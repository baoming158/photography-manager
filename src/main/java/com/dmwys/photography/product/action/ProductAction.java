package com.dmwys.photography.product.action;

import com.dmwys.photography.framework.page.PageModel;
import com.dmwys.photography.product.model.ProductModel;
import com.dmwys.photography.services.PhotoService;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@IocBean
@InjectName("productAction")
public class ProductAction {

	private static Logger LOGGER = LoggerFactory.getLogger(ProductAction.class);

	@Inject(value = "refer:photoService")
	private PhotoService photoService;

	@At("/showProduct")
	@Ok("jsp:/photo/showProduct")
	public void showProduct(HttpServletRequest request, @Param("currentPage") Integer currentPage) {
		if (currentPage == null || currentPage == -1) {
			currentPage = 1;
		}
		String id = request.getParameter("id");
		String context = request.getParameter("context");
		SqlExpressionGroup group = new SqlExpressionGroup();

		if (StringUtils.isNotBlank(id)) {
			group.and("id", "=", id);
			request.setAttribute("id", id);
		}
		if (StringUtils.isNotBlank(context)) {
			group.and("context", "like", "%" + context + "%");
			request.setAttribute("context", context);
		}
		group.and("del_flag","=",0);
		List<ProductModel> list = photoService.searchByPage(ProductModel.class,
				Cnd.where(group).desc("id"), currentPage, 10);

		String tatal = request.getParameter("count");
		int count = 0;
		if (tatal == null) {
			count = photoService.searchCount(ProductModel.class, Cnd.where(group));
		} else {
			count = Integer.parseInt(tatal);
		}
		PageModel<ProductModel> pm = new PageModel<ProductModel>(request, list, count);
		request.setAttribute("pm", pm);
	}

}
