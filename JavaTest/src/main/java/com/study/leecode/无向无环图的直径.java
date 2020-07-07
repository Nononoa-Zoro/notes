package com.study.leecode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

//图的深度优先遍历
//求无向无环图的直径：图中两点之间最大的边数
public class 无向无环图的直径 {

    static class Graphic {
        int n;
        int[][] edge;
        boolean[] visited;

        public Graphic(int n) {
            this.n = n;
            this.edge = new int[n][n];
            this.visited = new boolean[n];
        }

        //        public void dfs(){
//            Stack<Integer> stack = new Stack<>();
//            stack.push(0);
//            visited[0]=true;
//            while(!stack.isEmpty()){
//                Integer i = stack.pop();
//                System.out.println(i);
//                for(int j=0;j<n;j++){
//                    if(edge[i][j]==1&& !visited[j]){
//                        visited[j]=true;
//                        stack.push(j);
//                    }
//                }
//            }
//        }
        public void addEdge(int i, int j) {
            this.edge[i][j] = 1;
            this.edge[j][i] = 1;
        }
    }

    static int max = 0;

    static List<Integer> res = new ArrayList<>();

    public static void dfs(int[][] edge, boolean[] visited, int deep, int x, int n) {
        //list表示x的相邻节点
        List<Integer> list = find(x, edge);
        boolean flag = false;
        //遍历相邻节点 如果相邻节点全部访问过 则结束递归
        for (Integer i : list) {
            if (!visited[i]) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            //max表示从节点x开始图的最大深度
            max = Math.max(deep, max);
            res.add(deep);
            return;
        }

        for (int i = 0; i < n; i++) {
            //如果x有相邻节点且该节点没有被访问过则递归
            if (edge[x][i] == 1 && !visited[i]) {
                //该节点访问位置为true
                visited[i] = true;
                //深度+1
                deep++;
                dfs(edge, visited, deep, i, n);
                deep--;
            }
        }

    }

    //找到索引为x的相邻节点 返回List
    public static List<Integer> find(int x, int[][] edge) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < edge.length; i++) {
            if (edge[x][i] == 1) list.add(i);
        }
        return list;
    }

    //找到出度为1的节点返回List
    public static List<Integer> findEndPoint(int[][] edge) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < edge.length; i++) {
            for (int j = 0; j < edge[0].length; j++) {
                if (edge[i][j] == 1) count++;
                if (count == 2) break;
            }
            if (count == 1) {
                list.add(i);
            }
            count = 0;
        }
        return list;
    }

    public static void main(String[] args)   {
        Graphic graphic = new Graphic(9);
        graphic.addEdge(0, 1);
        graphic.addEdge(1, 2);
        graphic.addEdge(2, 3);
        graphic.addEdge(3, 5);
        graphic.addEdge(1, 4);
        graphic.addEdge(4, 7);
        graphic.addEdge(7, 8);
        graphic.addEdge(4, 6);


        List<Integer> endPoint = 无向无环图的直径.findEndPoint(graphic.edge);
        System.out.println(endPoint);

        for(Integer x:endPoint){
            无向无环图的直径.dfs(graphic.edge, graphic.visited, 0, 5, graphic.edge.length);
        }

        Optional<Integer> value = res.stream().max(Comparator.naturalOrder());
        value.ifPresent(System.out::println);


    }
}
