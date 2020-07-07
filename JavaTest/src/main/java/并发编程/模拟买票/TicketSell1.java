package 并发编程.模拟买票;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟卖10000张票的过程
 */
public class TicketSell1 {

    //初始化1000张票 10个线程卖
    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    //错误 判断与操作分离
                    System.out.println("买票" + tickets.remove(0));
                }
            }, "thread" + i).start();
        }
    }


}
