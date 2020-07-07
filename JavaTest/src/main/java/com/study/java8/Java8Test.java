package com.study.java8;

import org.junit.Test;

import java.security.acl.Group;
import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

public class Java8Test {
    List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

    // filter sorted map collect
    @Test
    public void test11() {
        List<String> lowCaloriesMenu = menu.parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(lowCaloriesMenu);
    }

    //stream 只可以被访问一次
    @Test
    public void test12() {
        List<String> titles = Arrays.asList("java8", "in", "action");
        titles.stream().forEach(System.out::println);
        titles.stream().forEach(System.out::println);
    }

    //集合的外部迭代 VS stream的内部迭代
    @Test
    public void test13() {
        ArrayList<String> names = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            names.add(dish.getName());
        }
    }

    @Test
    public void test14() {
        List<String> titles = Arrays.asList("java8", "in", "action");
        List<Integer> list = titles.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    //flatMap的含义 将多个stream扁平化为一个流
    @Test
    public void test15() {
        String[] words = {"Hello", "World"};
        List<String> collect = Arrays.stream(words)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    //找到第一个平方能被三整除的数
    @Test
    public void test16() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = numbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        first.ifPresent(System.out::println);
    }

    //规约操作 求数组的和
    @Test
    public void test17() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    //规约操作 求数组中的最大值
    @Test
    public void test18(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(System.out::println);
    }

    //求list中的元素个数
    @Test
    public void test19(){
        Integer sum = menu.stream()
                .map(d -> 1)
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    @Test
    public void test20(){
        
    }
    @Test
    public void test1() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.compose(g);
        Integer r = h.apply(2);
        System.out.println(r);
    }

    @Test
    public void test2() {
        menu.stream().map(Dish::getName).forEach(System.out::println);
    }

    /**
     * 扁平流 flatMap 将流中的每一个值都转换成另一个流 然后将流合并成为一个流
     */
    @Test
    public void test3() {
        String[] s = {
                "Hello",
                "World"
        };
        List<String> collect = Arrays.stream(s).map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test4() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream().flatMap(i -> numbers2.stream()
                .filter(j -> (i + j) % 3 == 0)
                .map(j -> new int[]{i, j})
        ).collect(Collectors.toList());
    }

    @Test
    public void test5() {
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d -> System.out.println(d.getName()));
    }

    @Test
    public void test6() {
        //找到第一个平方可以被3整除的数
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.stream().map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst().ifPresent(x -> System.out.println(Math.sqrt(x)));

    }

    @Test
    public void test7() {
        //归约 将流中的各个数据归约成一个数
        int[] arr = new int[]{
                1, 2, 3, 4, 5
        };

        int res = Arrays.stream(arr).reduce(0, (c, d) -> c + d);
        System.out.println(res);
        Arrays.stream(arr).reduce(Integer::max).ifPresent(System.out::println);
        Integer reduce = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        System.out.println(reduce);
    }

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    @Test
    public void test8() {
        //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(t -> System.out.println(t.getValue()));
        //(2) 交易员都在哪些不同的城市工作过？
        List<String> res = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        //查找所有来自于剑桥的交易员，并按姓名排序
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        //有没有交易员是在米兰工作的
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("milan"));
        //打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        //所有交易中，最高的交易额是多少
        Optional<Integer> maxValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

    }

    @Test
    public void test9() {
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        Map<Dish.Type, Long> collect = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println(collect);
        Map<Dish.Type, Optional<Dish>> collect1 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.maxBy(comparingInt(Dish::getCalories))));
        System.out.println(collect1);
        Map<Dish.Type, Dish> collect2 = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(Collectors.maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(collect2);
        Map<Dish.Type, Integer> totalCalories = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.summingInt(Dish::getCalories)));
        System.out.println(totalCalories);

    }

    public boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.range(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    //判断一个数是不是质数
    @Test
    public void test10() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, n)
                .boxed()
                .collect(Collectors.partitioningBy(c -> isPrime(c)));
    }
}