package 并发编程.并发基础知识例子;

import java.util.concurrent.TimeUnit;

/**
 * 一般set 和 get方法都要加锁
 * 如果只给set方法加锁，那么有可能在set方法的过程中
 * 在set balance 之前 get balance 此时得到的数据是不对的。也就是脏数据
 */
public class Account {
    String name;
    double balance;

    public synchronized void setBalance(String name , double balance){

        this.name = name;

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        this.balance = balance;

    }

    public synchronized double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {

        Account account = new Account();

        new Thread(()->{account.setBalance("zhangsan",100);}).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan"));
    }
}
