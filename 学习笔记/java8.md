### java8

lambda表达式中可以使用局部变量，但是局部变量必须是final类型的或者是只赋值过一次。

#### 方法引用

是调用指定方法的lambda的一种快捷的写法，是让你根据现有的方法创建Lambda表达式。目标引用放在：：前，方法的名称放在后面。

#### 比较器复合

```java
 apples.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getColor));
```

thenComparing可以在第一个比较条件相同的条件下，判断第二个条件。

#### 谓词复合(and or negate)

```java
        Predicate<Apple> redApple = (Apple apple) -> {
            return "red".equals(apple.getColor());
        };
        redApple.and(a -> a.getWeight() > 150);
```

### 复合函数

```java
        //函数复合 也就是复合函数f(g(x)) g(f(x))
        Function<Integer, Integer> f = y -> y + 1;
        Function<Integer, Integer> g = q -> q * 2;
        Function<Integer,Integer> h = f.andThen(g);//g(f(x))
        System.out.println(h.apply(1));//4
        Function<Integer,Integer> h1 = f.compose(g);//f(g(x))
        System.out.println(h1.apply(1));//3
```

### 函数式数据处理

##### 流与集合的区别

1. 集合是存储在内存中的数据结构，它包含的值需要计算出来之后才存到内存中。也就是说，集合中的元素在一开始就是固定的大小。

    流是按需索取的，类似生产者-消费者。流是在概念上固定的数据结构，你只能按需索取，这些值会在用户看不见的地方生成。

​       集合就好比使用DVD看电影，要将DVD中的数据全部读出来才开始看。

​       流好比在互联网使用在线流媒体看电影，当前只需要记录自己需要的值。

2. 集合采用的是外部迭代方式，流采用的是内部迭代方式。外部迭代需要自己决定数据并行处理的方式，内部迭代会交给stream选择一种合适的方式并行处理数据。

#### 中间操作

##### filter 筛选  Predicate<T > T->boolean

##### sorted 排序 Comparator<T> (T,T)->int

##### map 映射 Function<T,R>  T->R

##### limit 返回的元素个数不超过n

##### skip 跳过前n个元素

##### distinct

##### allMatch、anyMatch、noneMatch、findFirst、findAny

#### 终端操作

##### foreach

#####count

##### collect



