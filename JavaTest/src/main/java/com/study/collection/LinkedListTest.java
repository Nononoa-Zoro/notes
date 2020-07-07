package com.study.collection;

import java.util.*;

public class LinkedListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+"");
        }
        Iterator<String> iterator = list.iterator();
        int j=0;
        while (iterator.hasNext()){
            if(j==3)list.remove(j);
            System.out.println(iterator.next());
            j++;
        }
    }
}
