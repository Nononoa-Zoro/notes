package com.study.leecode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Huawei02 {
    static class Node {
        String addr;
        String mask;
        String val;

        public Node() {
        }

        public Node(String addr, String mask, String val) {
            this.addr = addr;
            this.mask = mask;
            this.val = val;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getMask() {
            return mask;
        }

        public void setMask(String mask) {
            this.mask = mask;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        String target = s[0];
        StringBuilder str = new StringBuilder(s[1]);


        List<Node> list = new ArrayList<>();

        while (str.length() > 0) {
            int i = str.indexOf("[");
            if (i == -1) {
                System.out.println("FAIL");
                return;

            }
            int j = str.indexOf("]");
            String head = str.substring(0, i);

            if (head.equals(target)) {
                Node node = new Node();
                int x = str.indexOf("addr=");
                int y = str.indexOf("mask=");
                int z = str.indexOf("val=");
                if (x != -1 && y != -1 && z != -1) {
                    String addr = str.substring(x, y - 1).substring(5);
                    if (addr.startsWith("0x") || addr.startsWith("0X") && isHexNumber(addr)) {
                        node.setAddr(addr);
                    }
                    String mask = str.substring(y, z - 1).substring(5);
                    if(mask.startsWith("0x")||mask.startsWith("0X")&&isHexNumber(mask)){
                        node.setMask(mask);
                    }
                    String val = str.substring(z, j).substring(4);
                    if(val.startsWith("0x")||val.startsWith("0X")&&isHexNumber(val)){
                        node.setVal(val);
                    }
                    list.add(node);
                }
            }

            if (j + 2 < str.length()) {
                str = str.delete(0, j + 2);
            } else {
                str = str.delete(0, j + 1);
            }

        }

        if (list.size() == 0) {
            System.out.println("FAIL");
            return;
        }

        for (Node node : list) {
            if (node.getAddr() != null && node.getMask() != null && node.getVal() != null) {
                System.out.printf("%s %s %s\r\n", node.getAddr(), node.getMask(), node.getVal());
            }else{
                System.out.println("FAIL");
                return;
            }
        }

    }

    //十六进制
    private static boolean isHexNumber(String str) {
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            char cc = str.charAt(i);
            if (cc == '0' || cc == '1' || cc == '2' || cc == '3' || cc == '4' || cc == '5' || cc == '6' || cc == '7' || cc == '8' || cc == '9' || cc == 'A' || cc == 'B' || cc == 'C' ||
                    cc == 'D' || cc == 'E' || cc == 'F' || cc == 'a' || cc == 'b' || cc == 'c' || cc == 'd' || cc == 'e' || cc == 'f') {
                flag = true;
            }
        }
        return flag;
    }




}
