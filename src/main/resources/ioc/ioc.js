var ioc = {
	photoService : {
		type : "com.dmwys.photography.services.PhotoService",
		fields : {dao : {refer : 'photo_dao'}}
	}
};
