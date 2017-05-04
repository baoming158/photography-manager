package com.dmwys.photography.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    private static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String getMd5(byte[] byteBuffer) {
        String md5String = "";
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(byteBuffer);
            byte[] res = mdInst.digest();
            md5String = toHexString(res);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5String;
    }

    public static String getMD5(File file)  {
    	String md5 = "";
		try {
			md5 = getMD5(file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return md5;
    }
	public static String getMD5(String filePath) throws Exception {
        File file = new File(filePath);
        if(!file.exists()) {
            return null;
        }

        InputStream fis = null;
        try {
            fis = new FileInputStream(filePath);

            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;

            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);

            return toHexString(complete.digest());
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                }
        }
    }
	public static void main(String[] a){
	    String s = MD5Util.getMd5("abc".getBytes());
	    System.out.println(s);
	}
}
