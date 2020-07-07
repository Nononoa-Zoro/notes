package 并发编程.并发容器;

import java.util.concurrent.LinkedTransferQueue;

/**
 * LinkedTransferQueue 生产者向队列中放元素的时候，如果有消费者，直接给消费者。
 */
public class T08_TransferQueue {
	public static void main(String[] args) throws InterruptedException {
		LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
		
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		strs.transfer("aaa");//直接给消费者，transfer调用之前必须要有消费者，不然会阻塞。
		
//		strs.put("aaa");
		

/*		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/
	}
}
