var chain = {
	"default" : {
		"ps" : [
				"org.nutz.mvc.impl.processor.UpdateRequestAttributesProcessor",
				"org.nutz.mvc.impl.processor.EncodingProcessor",
				"org.nutz.mvc.impl.processor.ModuleProcessor",
				"org.nutz.mvc.impl.processor.ActionFiltersProcessor",
				"org.nutz.mvc.impl.processor.AdaptorProcessor",
				"org.nutz.mvc.impl.processor.MethodInvokeProcessor",
				"org.nutz.mvc.impl.processor.ViewProcessor" ],
		"error" : 'com.dmwys.photography.processor.FailProcessor'
	}
};