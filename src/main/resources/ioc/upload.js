var ioc = {
  utils: {
    type: 'com.dmwys.photography.util.ResourceHelper',
    fields: {
      sc: {
        app: '$servlet'
      }
    }
  },
  tmpFilePool: {
    type: 'org.nutz.filepool.NutFilePool',
    args: [{
      java: '$utils.get("tmp_file_path")'
    }, 100000]
  },
  uploadFileContext: {
    type: 'org.nutz.mvc.upload.UploadingContext',
    singleton: false,
    args: [{
      refer: 'tmpFilePool'
    }],
    fields: {
      ignoreNull: true,
     //maxFileSize: 1048576, // 1M
      maxFileSize: 200000, // 300kb
      nameFilter: '^(.+[.])(jpg|gif|png|bmp|jpeg|jpeg2000|tiff)$'
    }
  },
  cardconnectContext: {
	    type: 'org.nutz.mvc.upload.UploadingContext',
	    singleton: false,
	    args: [{
	      refer: 'tmpFilePool'
	    }],
	    fields: {
	      ignoreNull: true,
	      maxFileSize: 15360000000, // 300kb
	      nameFilter: '^(.+[.])(jpg|gif|png|bmp|jpeg|jpeg2000|tiff)$'
	    }
	  },
  cardconnectFileUpload: {
	    type: 'org.nutz.mvc.upload.UploadAdaptor',
	    singleton: false,
	    args: [{
	      refer: 'cardconnectContext'
	    }]
	  },
  myFileUpload: {
    type: 'org.nutz.mvc.upload.UploadAdaptor',
    singleton: false,
    args: [{
      refer: 'uploadFileContext'
    }]
  },
  excelFileUpload: {
	    type: 'org.nutz.mvc.upload.UploadAdaptor',
	    singleton: false,
	    args: [{
	      refer: 'uploadExcelFileContext'
	    }]
	  },
	  uploadExcelFileContext: {
		    type: 'org.nutz.mvc.upload.UploadingContext',
		    singleton: false,
		    args: [{
		      refer: 'tmpFilePool'
		    }],
		    fields: {
		      ignoreNull: true,
		      maxFileSize: 1048576, // 1M
		      nameFilter: '^(.+[.])(xls|xlsx)$'
		    }
		  },
  apkFileContext: {
    type: 'org.nutz.mvc.upload.UploadingContext',
    singleton: false,
    args: [{
      refer: 'tmpFilePool'
    }],
    fields: {
      ignoreNull: true,
      maxFileSize: 1048576000, // 1000M
      nameFilter: '^(.+[.])(apk|APK|JAR|jar)$'
    }
  },
  apkUpload: {
    type: 'org.nutz.mvc.upload.UploadAdaptor',
    singleton: false,
    args: [{
      refer: 'apkFileContext'
    }]
  },
  allFileContext: {
    type: 'org.nutz.mvc.upload.UploadingContext',
    singleton: false,
    args: [{
      refer: 'tmpFilePool'
    }],
    fields: {
      ignoreNull: true,
      maxFileSize: 1048576, // 1M
      nameFilter: '.*'
    }
  },
  allUpload: {
    type: 'org.nutz.mvc.upload.UploadAdaptor',
    singleton: false,
    args: [{
      refer: 'allFileContext'
    }]
  }
};
