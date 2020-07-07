package com.study.leecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class leecode_20有效的括号 {

    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};

    public static boolean isValid(String s) {

        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                Character top = map.get(stack.peek());
                if (top == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(])";
        boolean res = isValid(s);
        System.out.println(res);
    }
}

