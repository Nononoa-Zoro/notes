package com.study.leecode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class leecode_820单词压缩编码_前缀树 {

    //定义前缀树的节点
    public static class TrieNode {
        TrieNode[] children;
        int count;

        TrieNode() {
            children = new TrieNode[26];
            count = 0;
        }
    }

    //前缀树的插入操作
    public static class Trie {
        //持有一个指向根节点的信息
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        public int insert(String word) {
            TrieNode cur = root;
            boolean isNew = false;
            //根据题目 倒着插入
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                //判断当前节点有没有指向对应字符的节点
                if (cur.children[c] == null) {
                    isNew = true;
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            return isNew ? word.length() + 1 : 0;
        }
    }

    public static int minimumLengthEncoding(String[] words) {
        int len = 0;
        Trie trie = new Trie();
        Arrays.sort(words, (o1, o2) -> o2.length() - o1.length());
        for (String word : words) {
            len += trie.insert(word);
        }
        return len;
    }

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        int res = minimumLengthEncoding(words);
        System.out.println(res);
    }
}
