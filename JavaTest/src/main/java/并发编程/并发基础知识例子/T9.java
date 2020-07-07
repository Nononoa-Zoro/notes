package 并发编程.并发基础知识例子;

/**
 * 不要以字符串常量作为锁
 * 下面的s1和s2其实是同一把锁
 */
public class T9 {

    String s1 = "hello";
    String s2 = "hello";

    void m1(){
        synchronized (s1){

        }
    }

    void m2(){
        synchronized(s2){

        }
    }
}
