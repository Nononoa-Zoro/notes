### 线性表

####LinkedList：删除和添加元素有优势，因为不需要移动元素。

  ```java
      /**
       * Returns the (non-null) Node at the specified element index.
       */
      Node<E> node(int index) {
          // assert isElementIndex(index);
  
          if (index < (size >> 1)) {
              Node<E> x = first;
              for (int i = 0; i < index; i++)
                  x = x.next;
              return x;
          } else {
              Node<E> x = last;
              for (int i = size - 1; i > index; i--)
                  x = x.prev;
              return x;
          }
      }
  ```

  LinkedList不是线程安全的，使用Collections.Synchronized可以将LinkedList变为多线程安全的。

  ```java
  //Collections.Synchronized其实就是使用Synchronized关键字同步多线程操作
  final Object mutex;     // Object on which to synchronize
   public E get(int index) {
              synchronized (mutex) {return list.get(index);}
          }
  ```

####LinkedList的迭代器
listIterator：可以返回一个从任意索引位置开始的迭代器。支持fail-fast机制（如果在这个迭代器被创建之后，这个list的结构仍然被改变了，比如删除了这个list中的元素。就会抛出ConcurrentModificationException异常）


####ArrayList
底层实现是数组，是一个自动扩容的数组，默认大小10
```java
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
```
iterator():返回一个迭代器，只能从前往后迭代访问元素。

listIterator():返回一个迭代器可以双向访问

```java
    /**
  		扩容
  		先将原数组的大小变为1.5倍，如果比你想要的还要小，那就将原数组扩容为你给定的值。
  		如果比你给定的值大，那就取这个值。
  		
  		比如：你现在的数组大小为12，现在你要20。那么，首先ArrayList会计算12*1.5=18<20所以就直接给你一个大小为20的数组
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```

###vector
- 底层是一个数组，是多线程安全的。
- 两种迭代器
	1. Iterator：一个迭代器可实现从前往后访问元素
	2. listIterator：一个迭代器可以从指定位置从前往后或从后往前遍历数据
- Spliterator（可分割迭代器）：多线程遍历元素的迭代器，默认将原集合划分为大小相等的两部分。
###PriorityQueue
- 优先，无界队列。**堆结构**，在构造时传入Comparator可以按照固定规则排序。或者待添加元素自身实现了Comparable接口。

- Comparable接口

  ```java
  //o1.compareTo(o2);
  //返回正数的话，o1要排在比较对象o2后面，返回负数的话，放在前面。
  //返回正数o1放后面，返回负数o1放前面
  @Override
      public int compareTo(Test o) {
          return this.i - o.i;
      }
  ```

###HashSet
- HashSet通过hashcode和equals方法判断两个对象是否是一样的。
	1. 首先判断对象的散列码（hashcode)是否一致。如果不一致，则表示两个对象是不一样的，可以加入set中。否则进入步骤2。
	2. 如果对象的散列值一致，则比较对象的equals方法。如果equals方法返回true表示二者是同一个对象，所以不能加入set。否则加入set中。
###LinkedHashSet
1. 可按照插入顺序或是访问顺序进行迭代访问。

###LinkedHashMap
有顺序的HashMap，默认顺序是数据的插入顺序。（插入顺序和访问顺序），如果访问了LinkedHashMap中的元素，那么这个元素就会放在数组的最后一个位置。
```java
//默认构造函数
public LinkedHashMap() {
        super();
        accessOrder = false;//不按照访问顺序存储
    }
```

LinkedHashMap重新了HashMap的reinitialize()方法，将head和tail初始化为空，其实就是初始化一个双向链表且只有头节点

```java
   
    /**
     * The head (eldest) of the doubly linked list.
     */
    transient LinkedHashMap.Entry<K,V> head;

    /**
     * The tail (youngest) of the doubly linked list.
     */
    transient LinkedHashMap.Entry<K,V> tail;

	// overrides of HashMap hook methods
    void reinitialize() {
        super.reinitialize();
        head = tail = null;
    }

    /**
     * 
     * LinkedHashMap的内部类，是一个双向链表
     */
    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }

```
LinkedHashMap的put方法还是调用的HashMap的put方法，但是如果要向HashMap中添加新的元素，则需要调用afterNodeInsertion（）方法，该方法在HashMap中是空实现。LinkedHashMap重写了这个方法。

```java
    void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        if (evict && (first = head) != null && removeEldestEntry(first)) {
            K key = first.key;
            removeNode(hash(key), key, null, false, true);
        }
    }
```

### ConcurrentHashMap

```java
    /**
     * Table initialization and resizing control.  When negative, the
     * table is being initialized or resized: -1 for initialization,
     * else -(1 + the number of active resizing threads).  Otherwise,
     * when table is null, holds the initial table size to use upon
     * creation, or 0 for default. After initialization, holds the
     * next element count value upon which to resize the table.
     */
     //-1表示table在初始化 -(n+1)表示有n个线程在resize，其他值表示table的默认容量
    private transient volatile int sizeCtl;
```

```java
    /**
     * Initializes table, using the size recorded in sizeCtl.
     */
	//初始化表
    private final Node<K,V>[] initTable() {
        Node<K,V>[] tab; int sc;
        while ((tab = table) == null || tab.length == 0) {
            if ((sc = sizeCtl) < 0)//表示有线程在执行表的初始化操作，Thread.yield让出时间片
                Thread.yield(); // lost initialization race; just spin
            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
                try {
                    if ((tab = table) == null || tab.length == 0) {
                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                        @SuppressWarnings("unchecked")
                        Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                        table = tab = nt;
                        sc = n - (n >>> 2);//默认大小0.75*size
                    }
                } finally {
                    sizeCtl = sc;
                }
                break;
            }
        }
        return tab;
    }
```

```java
//假设table已经初始化完成，put操作采用==CAS+synchronized==实现并发插入或更新操作：
- 当前bucket为空时，使用CAS操作，将Node放入对应的bucket中。
- 出现hash冲突，则采用synchronized关键字。倘若当前hash对应的节点是链表的头节点，遍历链表，若找到对应的node节点，则修改node节点的val，否则在链表末尾添加node节点；倘若当前节点是红黑树的根节点，在树结构上遍历元素，更新或增加节点。
- 倘若当前map正在扩容f.hash == MOVED， 则跟其他线程一起进行扩容

final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) {
            Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0)
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {// 当前bucket为空
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            else if ((fh = f.hash) == MOVED)// 当前Map在扩容，先协助扩容，在更新值。
                tab = helpTransfer(tab, f);
            else {// hash冲突
                V oldVal = null;
                synchronized (f) {//同步
                    if (tabAt(tab, i) == f) {// 链表头节点
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                if (e.hash == hash &&// 节点已经存在，修改链表节点的值
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {// 节点不存在，添加到链表末尾
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {// 红黑树根节点
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD)//链表节点超过了8，链表转为红黑树
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);// 统计节点个数，检查是否需要resize
        return null;
    }
```

```java
//hash算法
static final int HASH_BITS = 0x7fffffff; // usable bits of normal node hash
static final int spread(int h) {return (h ^ (h >>> 16)) & HASH_BITS;}
//定位索引
int index = (n - 1) & hash  // n为bucket的个数
```

### HashMap

```java
//计算key的hash值 高16位与低16位进行异或    
static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
}
static int indexFor(int h, int length) { 
     return h & (length-1);  //得到的hash值对数组长度取模 得到数组索引
}
```

