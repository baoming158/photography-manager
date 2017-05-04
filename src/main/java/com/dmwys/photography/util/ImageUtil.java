package com.dmwys.photography.util;

import java.io.File;
import java.util.Date;

import org.nutz.mvc.upload.TempFile;

public class ImageUtil {
	private static String file_path=ResourceHelper.get("image_dir");// /data0/ky/data/media/kytv/
	private static String image_api_url= ResourceHelper.get("image_api_url");// http://mis.testtv.kuyun.com/media/kytv/
	/**
	 * 
	 * @param img 图片对象
	 * @param folderName 保存的文件夹名称
	 * @return
	 */
	private static long seq = 0 ;
	public static String getImg(TempFile img,String folderName){
		File file = img.getFile();
		String path = file.getAbsolutePath();
		String suffix = path.substring(path.lastIndexOf("."));// .png
		String newFile ="";
		if("/ky/img_test_repo/".equals(file_path)){
			newFile = "/ky/img_repo/"   + folderName + File.separatorChar +"test-img"+File.separator+ DateUtil.formatDateToString(new Date(), "yyyyMMdd") ;
		}else{
			newFile = file_path  + folderName + File.separatorChar + DateUtil.formatDateToString(new Date(), "yyyyMMdd") ;
		}
		String newFileName = System.currentTimeMillis() + "_" + (seq++)%1000 + suffix ;
		File fileNew = new File(newFile);
		if(!fileNew.exists()){
			fileNew.mkdirs();
		}
		String finalpath =newFile + File.separatorChar + newFileName;
		file.renameTo(new File(finalpath));
		String result = "";
		if("/ky/img_test_repo/".equals(file_path)){
			result = finalpath.replace("/ky/img_repo/",image_api_url);
		}else{
			result = finalpath.replace(file_path,image_api_url);
		}

		try {
            CdnApi.commitCDN(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return  result;
	}
	public static void deleteImg(String img){
//		http://mis.testtv.kuyun.com/media/kytv/general/20150129/1422498983888_0.png
//		if(StringUtils.isNotBlank(img)){
//			String file = img.replace(media_api_url, file_path);
//			File f = new File(file);
//			if(f.exists()){
//				f.delete();
//			}
//		}
	}
	public static void main(String[] args) {
		String a = "/ky/image_repo/image/sadfas.jpg";
		String c = "/ky/image_repo/";
		String b = "http://img.videodot.kuyun.com/";
		System.out.println(a.replace(c, b));
		
	}
}
