package com.dmwys.photography.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Author : baoming  28/03/2017.
 * description :
 */
public class DistinctTypesFromTxtFile {
    public static void main(String[] args) throws Exception {
        //读取文本文件中内容
        //将内容用，切割放入HashSet 集合中
        //遍历集合元素输出
        HashSet types = new HashSet();
        String encoding = "UTF-8";
        String filePath = "/Users/baoming/bb";
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            int i = 1;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                String content[] = lineTxt.split(",");
                for (String b : content) {
                    if (StringUtils.isNotBlank(b)) {
                        types.add(b);
                        System.out.println("line " + i + b);
                    }
                }
                i++;
            }
            read.close();

        }
        String typeStr = "";
        for(Iterator it=types.iterator();it.hasNext();){
            System.out.println(it.next());
        }
    }

}
