package 并发编程.模拟买票;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

//使用并发容器改造

public class TicketSell4 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s == null) break;
                    else System.out.println("销售了: " + s);
                }
            }, "thread" + i).start();
        }
    }


}
