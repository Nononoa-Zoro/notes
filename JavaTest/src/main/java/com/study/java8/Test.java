package com.study.java8;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        //在lambda表达式中使用的局部变量必须是final的
//        final int portNumber = 123456;
//        Runnable r = ()->{
//            System.out.println(portNumber);
//        };
        Random random = new Random();
        int x = random.nextInt(2);
        ArrayList<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Apple apple = x == 1 ? new Apple("red", i * 10) : new Apple("green", i * 10);
            apples.add(apple);
        }
        apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
        //谓词复合
        Predicate<Apple> redApple = (Apple apple) -> {
            return "red".equals(apple.getColor());
        };
        redApple.and(a -> a.getWeight() > 150);

        //函数复合 也就是复合函数f(g(x)) g(f(x))
        Function<Integer, Integer> f = y -> y + 1;
        Function<Integer, Integer> g = q -> q * 2;
        Function<Integer,Integer> h = f.andThen(g);//g(f(x))
        System.out.println(h.apply(1));//4
        Function<Integer,Integer> h1 = f.compose(g);//f(g(x))
        System.out.println(h1.apply(1));//3

    }

}
