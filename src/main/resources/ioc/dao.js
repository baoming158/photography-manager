var ioc = {
	    config : {
	        type : "org.nutz.ioc.impl.PropertiesProxy", 
	        fields : {
	            paths : ["configure.properties"] 
	        }
	    },
    photo_dataSource : {
	        type :"com.alibaba.druid.pool.DruidDataSource",
	        events : {
	            depose :"close"
	        },
	        fields : {
	            driverClassName : {java :"$config.get('db.photo.driver')"},
	            url 			: {java :"$config.get('db.photo.url')"},
	            username 		: {java :"$config.get('db.photo.user')"},
	            password		: {java :"$config.get('db.photo.password')"},
	            initialSize     : 5,//
	            minIdle			: 3,
	            maxActive       : 30,
	            maxWait			: 60000,
	            timeBetweenEvictionRunsMillis : 60000,
	            minEvictableIdleTimeMillis	: 300000,
                filters : "mergeStat,wall",
	            testOnReturn    : true,
	            validationQuery : "select CURRENT_DATE"
	        }
	    },
	// Dao
    photo_dao: {
        type :'org.nutz.dao.impl.NutDao',
        args : [ {refer :"photo_dataSource"}]
    }
}