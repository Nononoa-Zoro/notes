package 栈;

import java.util.Stack;

/*https://leetcode-cn.com/problems/largest-rectangle-in-histogram/*/
public class leecode_84柱状图中最大的矩形 {

    private static final int[] arr = {2,1,5,6,2,3};

    public static void main(String[] args) {
        int res = largestRectangleArea(arr);
        System.out.println(res);
    }

    public static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];

        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<len;i++){
            while (!stack.isEmpty()&&heights[i]<=heights[stack.peek()]){
                stack.pop();
            }
            if (stack.isEmpty()){
                left[i]=-1;//不存在左边比自己小的 记-1
            }else{
                left[i]=stack.peek();
            }
            stack.push(i);
        }

        while (!stack.isEmpty()){
            stack.pop();
        }

        for (int i=len-1;i>=0;i--){
            while (!stack.isEmpty()&&heights[i]<=heights[stack.peek()]){
                stack.pop();
            }
            if (stack.isEmpty()){
                right[i]=len;//右边不存在比自己小的 记为len
            }else{
                right[i]=stack.peek();
            }
            stack.push(i);
        }

        int area = 0 ;
        for(int i = 0;i<len;i++){
            area= Math.max(area,heights[i]*(right[i]-left[i]-1));
        }
        return area;
    }
}
