package 并发编程.并发容器;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {

	//有界的阻塞队列
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
		}
		
		strs.put("aaa");//如果阻塞队列满会一直阻塞
//		strs.add("aaa");//如果队列满直接抛异常


//		boolean isSuccess = strs.offer("aaa");//不会抛异常 只会返回一个boolean表示是否添加成功
//		System.out.println(isSuccess);

//		strs.offer("aaa", 1, TimeUnit.SECONDS);//在给定的时间内阻塞
		
		System.out.println(strs);
	}
}
