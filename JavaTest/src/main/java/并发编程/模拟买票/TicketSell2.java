package 并发编程.模拟买票;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 思考将ArrayList改成vector之后能不能完成这个功能？
 *
 * 不能
 *
 * 判断和减票操作是分离的 这两者不是原子性操作
 *
 * size 和 remove 方法是原子的 但是这二者之间不是原子操作 所以会出问题
 *
 */
public class TicketSell2 {

    static Vector<String> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) tickets.add("票编号" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("卖票 -> " + tickets.remove(0));
                }
            }, "thread" + i).start();
        }
    }


}
