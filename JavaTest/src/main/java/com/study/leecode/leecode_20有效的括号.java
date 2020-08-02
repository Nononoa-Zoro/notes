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

    // 栈里边儿的元素只会存放左括号
    public static boolean isValid(String s) {

        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;

        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) {
                // 如果是左括号就压栈
                stack.push(c);
            } else {
                // 如果是右括号，判断栈顶元素是不是对应的左括号
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

