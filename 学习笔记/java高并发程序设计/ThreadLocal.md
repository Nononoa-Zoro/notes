###ThreadLocal

通过增加资源的方式保证所有对象的线程安全。是一个线程的局部变量，只有当前线程可以访问这个变量。

例子：SimpleDateFormat.parse()方法不是线程安全的，一般会在这个方法的前后加锁。但是也可以使用ThreadLocal为每一个线程分配一个这个对象。

ThreadLocal对象是一个类似HashMap的数据结构，其中key时当前线程，value就是与线程关联的对象。

如果使用线程池，有些对象不一定会回收。记得调用ThreadLocal.remove方法。

```java
class Thread{
        ThreadLocal.ThreadLocalMap threadLocals = null;
...
    
}
```

```java
public class ThreadLocal<T>{
   static class ThreadLocalMap {
     static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }
       }
}
```

