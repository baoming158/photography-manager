package com.dmwys.photography.product.service;

import com.dmwys.photography.services.PhotoService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@IocBean
public class ProductService {
	private static Logger LOGGER =  LoggerFactory.getLogger(ProductService.class);

	@Inject(value = "refer:photoService")
	private PhotoService photoService;
	

}


