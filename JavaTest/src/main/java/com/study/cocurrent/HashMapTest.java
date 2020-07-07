package com.study.cocurrent;

import org.junit.Test;

import java.util.*;

/**
 * 两种遍历hashmap的方式
 *
 * 1.entrySet 保存了hashMap的key 和 value
 *
 * 2.keySey 保存了hashMap的所有key 但是需要在使用时从map中获取key对应的value
 */
public class HashMapTest {

    //hashmap 遍历
    @Test
    public void test() {
        HashMap<Integer, String> map = new HashMap<>();
        for(int i=0;i<10;i++){
            map.put(i,"map"+i);
        }

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey()+": "+entry.getValue());
        }

        Iterator<Integer> iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()){
            Integer key = iterator1.next();
            System.out.println(key+" : "+map.get(key));
        }
    }

    @Test
    public void test1(){
        HashMap<String, String> map = new HashMap<>();
        map.put("name1","map1");
        map.put("name2","map2");
        map.put("name3","map3");
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> node = iterator.next();
            System.out.println(node.getKey()+" : "+node.getValue());
        }


    }

    @Test
    public void test2(){
        LinkedHashMap<String, String> lhp = new LinkedHashMap<>();
        lhp.put("name1","map1");
        lhp.put("name2","map2");
        lhp.put("name3","map3");
        Iterator<Map.Entry<String, String>> iterator1 = lhp.entrySet().iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, String> node = iterator1.next();
            System.out.println(node.getKey()+" : "+node.getValue());
        }
    }
}
