package com.study.leecode;

import java.util.Scanner;

public class 万万没想到之聪明的编辑 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        scanner.nextLine();
        while (cases-- > 0) {
            StringBuilder sb = new StringBuilder();
            String word = scanner.next();
            for (int j = 0; j < word.length(); j++) {
                if (sb.length() < 2) {
                    sb.append(word.charAt(j));
                    continue;
                }
                if (sb.length() >= 2) {
                    if (sb.charAt(sb.length() - 1) == word.charAt(j) && sb.charAt(sb.length() - 2) == word.charAt(j)) {
                        continue;
                    }
                }
                if (sb.length() >= 3) {
                    if (sb.charAt(sb.length() - 1) == word.charAt(j) && sb.charAt(sb.length() - 2) == sb.charAt(sb.length() - 3)) {
                        continue;
                    }
                }
                sb.append(word.charAt(j));
            }
            System.out.println(sb.toString());
        }
    }


}
