package 并发编程.并发容器;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T09_SynchronusQueue {

	//SynchronousQueue容量为0
	//生产者放一个，直接交给消费者消费。
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(()->{
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strs.put("aaa"); //
		//strs.add("aaa");
		System.out.println(strs.size());
	}
}
