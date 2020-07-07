package 并发编程.单例模式;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例工厂模式的两种实现方式
 */
public class SingletonFactory {
    private static Map<Class, Object> instances = new ConcurrentHashMap<>();

    //WeakReference<Object> 表示这是指向一个对象的弱引用 下次GC如果判断出这个对象是null则会回收
    private static Map<Class, WeakReference<Object>> weakReferences = new HashMap<>();

    //通过class返回一个指定类型的单例对象
    public static <E> E getInsatnce(Class<E> className) {
        Object instance = instances.get(className);
        if (instance == null) {
            synchronized (SingletonFactory.class) {
                instance = instances.get(className);
                if (instance == null) {
                    try {
                        instance = className.newInstance();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return (E) instance;
    }

    //创建一个可回收的单例模式
    public static <E> E getWeakInstance(Class<E> className) {
        WeakReference<Object> reference = weakReferences.get(className);
        Object instance = reference == null ? null : reference.get();
        if (instance == null) {
            synchronized (SingletonFactory.class) {
                instance = reference == null ? null : reference.get();
                if (instance == null) {
                    try {
                        instance = className.newInstance();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                    weakReferences.put(className, new WeakReference<Object>(instance));
                }
            }
        }
        return (E) instance;
    }
}
