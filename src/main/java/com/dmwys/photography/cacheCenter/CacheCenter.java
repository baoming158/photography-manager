package com.dmwys.photography.cacheCenter;

import java.util.concurrent.TimeUnit;

import com.dmwys.photography.util.FtlUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import freemarker.template.Template;

public class CacheCenter {
	public static LoadingCache<String, Template>	FtlCache	= CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
			.build(new CacheLoader<String, Template>(){
				public Template load(String key)  throws Exception {
					if(!key.endsWith(".ftl")){
						key = key + ".ftl";
					}
					return FtlUtil.getTemplate(key);
				}
			});
	
}
