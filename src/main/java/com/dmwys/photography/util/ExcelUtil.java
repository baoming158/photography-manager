package com.dmwys.photography.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.nutz.dao.entity.Record;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

public class ExcelUtil {
	
	public static final String templateFolder = ExcelUtil.class.getResource("/template/download").getFile().toString();
	
	public static void dotlistDownload(List<Record> list,String srcFilePath,String fileName,HttpServletResponse response) throws ParsePropertyException, InvalidFormatException, IOException{
		String tempPathFolder = ExcelUtil.class.getResource("/").getFile().toString();
		String tempFileName = UUID.randomUUID().toString();
		String tempPath = tempPathFolder + File.separator + tempFileName+".xlsx";
		Map<String, List<Record>> beanParams = new HashMap<String, List<Record>>();
		beanParams.put("list", list);
		//将文件按指定格式下载到临时文件
		XLSTransformer former = new XLSTransformer();
		former.transformXLS(srcFilePath, beanParams, tempPath);
		//
		doDownLoad(tempPath, fileName+".xls", response);
		//delete the template file
		deleteTempFile(tempPathFolder,tempFileName);
	}
	private static void deleteTempFile(String tempPathFolder,String tempFileName) {
		File folder = new File(tempPathFolder);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.getName().equals(tempFileName+".xlsx")) {
				file.delete();
			}
		}
	}

	// 下载
	public static void doDownLoad(String path, String name, HttpServletResponse response) {
		try {
			response.reset();
			response.setHeader("Content-disposition", "attachment;success=true;filename =" + URLEncoder.encode(name, "utf-8"));
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			OutputStream fos = null;
			InputStream fis = null;
			File uploadFile = new File(path);
			fis = new FileInputStream(uploadFile);
			bis = new BufferedInputStream(fis);
			fos = response.getOutputStream();
			bos = new BufferedOutputStream(fos);
			// 弹出下载对话框
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.flush();
			fis.close();
			bis.close();
			fos.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
