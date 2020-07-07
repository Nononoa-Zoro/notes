package 并发编程.单例模式;

//单例模式的实现3 double check
//不同步整个方法 减小锁粒度
public class Singleton3 {
    
    private volatile Singleton3 instance;

    private Singleton3() {

    }

    //double checkd 的好处是 多个线程并发的调用getInstance方法的时候
    //只需要第一次加锁，之后其他线程都不需要加锁 因为在外面判断instance都不为空 所以直接返回instance实例
    //double check 的思路类似 缓存击穿问题


    //缓存击穿是指，请求的数据不在缓存，这个时候需要请求数据库，但是如果这个时候请求某个数据的请求量很大
    //DB容易挂 所以我们采用double check 让唯一一个线程进DB查询数据，并且更新数据到缓存
    //这个时候其他的线程就不用访问DB了 因为缓存中已经有数据了
    public  Singleton3 getInstance() {
        //首先检查instance是否为空
        if (instance == null) {
            //加锁 保证只有一个线程访问下面代码 因为在上面和这里的代码之间有可能有其他的线程new了一个对象
            synchronized (Singleton3.class){
                if(instance==null){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
