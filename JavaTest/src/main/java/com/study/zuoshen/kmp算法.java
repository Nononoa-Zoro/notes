package com.study.zuoshen;

public class kmp算法 {

    public static void main(String[] args) {
        int index = getIndexOf("abcabce", "bca");
        System.out.println(index);
    }

    /**
     *
     * @param s 母串
     * @param m 子串
     * @return字串第一次出现在母串的位置
     */
    public static int getIndexOf(String s, String m) {
        //边界条件
        if (s == null || m == null || s.length() < m.length() || m.length() == 0) {
            return -1;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        //获得子串的next数组
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            //如果当前位置的字符相等
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                //-1表示当前字符就是首字符
                i1++;
            } else {
                //子串滑动next[i2]个单位
                i2 = next[i2];
            }

        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    /**
     * 根据字符数组求对应的next数组
     * next[i]=前i个字符串前缀和后缀的最大相等长度
     * @param str2
     * @return
     */
    public static int[] getNextArray(char[] str2) {

        //规定0位置值为-1 1位置值为0
        if (str2.length == 1) {
            return new int[]{-1};
        }

        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        //i位置前面的字符串最长cn前缀和最长cn后缀相等
        int cn = 0;
        while (i < str2.length) {
            //i-1和cn位置相等 next[i]=cn+1
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
