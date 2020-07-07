package com.study.zuoshen;

public class 前缀树 {

    public static class TrieNode {

        //有多少个字符串走过了当前节点
        public int path;

        //有多少字符串以这个节点结尾
        public int end;
        public TrieNode[] nexts;

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {

        private TrieNode root;

        //向前缀树插入一个字符串
        public void insert(String word) {
            if (word == null) return;
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.path++;
            }
            node.end++;
        }

        //从前缀树中删除一个字符串
        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for (char c : chs) {
                    index = c - 'a';
                    if (--node.nexts[index].path == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }

        }

        //查询一个字符串在前缀树中出现了几次
        public int search(String word) {
            if (word == null) return 0;
            char[] chs = word.toCharArray();
            TrieNode node = root;
            for (char c : chs) {
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
                node.path++;
            }
            return node.end;
        }

        //查找以word为前缀的字符串的数量
        public int prefixNumber(String word) {
            if (word == null) return 0;
            char[] chs = word.toCharArray();
            int index = 0;
            TrieNode node = root;
            for (char c : chs) {
                index = c - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }

    }
}
