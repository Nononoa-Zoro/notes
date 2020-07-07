package com.study.leecode;

import java.util.*;

public class Huawei01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(",");
        if (arr == null || arr.length == 0) {
            System.out.println("error.0001");
            return;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            char firstChar = s.toCharArray()[0];
            if (!Character.isUpperCase(firstChar)) {
                System.out.println("error.0001");
                return;
            }
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if(!Character.isLetter(chars[j])){
                    System.out.println("error.0001");
                    return;
                }
            }
            for (int j = 1; j < chars.length; j++) {
                if(!Character.isLowerCase(chars[j])){
                    System.out.println("error.0001");
                    return;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else {
                    if (o1.getKey().compareTo(o2.getKey()) < 0) {
                        return -1;
                    } else if (o1.getKey().compareTo(o2.getKey()) > 0) {
                        return 1;
                    } else if (o1.getKey().contains(o2.getKey())) {
                        return 1;
                    } else if (o2.getKey().contains(o1.getKey())) {
                        return -1;
                    }
                }
                return -1;
            }
        });

        System.out.println(list.get(0).getKey());
    }
}
