package 栈;

import java.util.Stack;

public class leecoede_71简化路径 {
    public static String simplifyPath(String path) {
        String[] arr = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s:arr){
            if (!s.equals("")&&!s.equals(".")&&!s.equals("..")){
                stack.push(s);
            }else if (!stack.isEmpty()&&s.equals("..")){
                stack.pop();
            }
        }
        StringBuilder builder = new StringBuilder();
        if (stack.isEmpty())return "/";
        for (int i=0;i<stack.size();i++){
            builder.append("/").append(stack.get(i));
        }
        return  builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));
    }
}
