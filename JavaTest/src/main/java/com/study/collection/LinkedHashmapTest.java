package com.study.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LinkedHashmapTest {
    public static void main(String[] args) {
        //hashmap存储数据是无序的
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("name1", "josan1");
//        hashMap.put("name2", "josan2");
//        hashMap.put("name3", "josan3");
//        Set<Map.Entry<String, String>> entries = hashMap.entrySet();
//        for (Map.Entry<String, String> entry : entries) {
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }

        //LinkedHashMap默认按照数据的插入顺序存储数据
//        LinkedHashMap<String, String> linkedHashMap  = new LinkedHashMap<>();
//        linkedHashMap.put("name1", "josan1");
//        linkedHashMap.put("name2", "josan2");
//        linkedHashMap.put("name3", "josan3");
//        Set<String> keySet = linkedHashMap.keySet();
//        for (String key : keySet) {
//            System.out.println(key+":"+linkedHashMap.get(key));
//        }

        LinkedHashMap<String, String> linkedHashMap  = new LinkedHashMap<>(16, 0.75f, true);
        linkedHashMap.put("name1", "josan1");
        linkedHashMap.put("name2", "josan2");
        linkedHashMap.put("name3", "josan3");
        System.out.println("开始时顺序：");
        printMap(linkedHashMap);
        System.out.println("通过get方法，导致key为name1对应的Entry到表尾");
        linkedHashMap.get("name1");
        printMap(linkedHashMap);
    }

    private static<k,v> void printMap(Map<k,v> map){
        Set<Map.Entry<k, v>> entries = map.entrySet();
        for (Map.Entry<k, v> entry : entries) {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
