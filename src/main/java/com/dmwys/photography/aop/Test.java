package com.dmwys.photography.aop;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.common.usermodel.LineStyle;

import java.text.Collator;
import java.util.*;

/**
 * Author : baoming  31/03/2017.
 * description :
 */
public class Test {

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>()
        {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
            {
                return (o1.getValue()).compareTo( o2.getValue() );
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }
    public static void main(String[] args) {


        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("name", "中国");
        Map<String, Object> map1=new HashMap<String, Object>();
        map1.put("name", "北京");
        Map<String, Object> map2=new HashMap<String, Object>();
        map2.put("name", "首都");
        Map<String, Object> map3=new HashMap<String, Object>();
        map3.put("name", "new york");

        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        listSort(list);
        System.out.println(list);
    }
    public static void listSort(List<Map<String, Object>> resultList) {
        Collections.sort(resultList, new Comparator<Map<String, Object>>() {

            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                String name1= MapUtils.getString(o1, "name");
                String name2=MapUtils.getString(o2, "name");
                Collator instance = Collator.getInstance(Locale.CHINA);
                return instance.compare(name1, name2);

            }
        });
    }
}
