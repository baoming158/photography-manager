package com.dmwys.photography.util;

import org.apache.commons.collections.MapUtils;
import org.nutz.dao.entity.Record;
import org.nutz.lang.Strings;

import java.text.Collator;
import java.util.*;

/**
 * Author : baoming  31/03/2017.
 * description :
 */
public class MapSortUtil {
    public static void listSort(List<Record> resultList) {
        Collections.sort(resultList, new Comparator<Record>() {
            public int compare(Record o1, Record o2) {
                String name1= MapUtils.getString(o1, "name");
                String name2=MapUtils.getString(o2, "name");
                boolean chinese1 = Strings.isChineseCharacter(name1.charAt(0));
                boolean chinese2 = Strings.isChineseCharacter(name2.charAt(0));
                if(chinese1 ^ chinese2){
                    return chinese1 ? -1 : 1;
                }
                Collator instance = Collator.getInstance(Locale.CHINA);
                return instance.compare(name1, name2);

            }
        });
    }
    public static void main(String[] args) {


        List<Record> list=new ArrayList<>();
        Record map=new Record();
        map.put("name", "中国");
        Record map1=new Record();
        map1.put("name", "北京");
        Record map2=new Record();
        map2.put("name", "首都");
        Record map3=new Record();
        map3.put("name", "new york");
        Record map4=new Record();
        map4.put("name","WBC");
        Record map5=new Record();
        map5.put("name","AWZ");

        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        listSort(list);
        System.out.println(list);
    }
}
