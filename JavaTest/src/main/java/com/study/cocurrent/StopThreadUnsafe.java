package com.study.cocurrent;

/**
 * Thread.stop() 会立即结束线程 并且释放该线程占用的所有锁
 *
 * 该方式结束线程太暴力 会导致数据不一致
 *
 * 一般会在线程中显示设置stopMe()方法
 */
public class StopThreadUnsafe {

    private static User u = new User();
    private static class User{
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User() {
            this.id = 0;
            this.name = "0";
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    private static class ChangeObjectThread extends Thread {

        private volatile boolean stop=false;

        private void stopMe(){
            this.stop=true;
        }
        @Override
        public void run()  {
           while (true){
               if(stop){
                   System.out.println("Thread is stop.");
                   break;
               }
               synchronized (u){
                   int v = (int) (System.currentTimeMillis() / 1000);
                   u.setId(v);
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   u.setName(String.valueOf(v));
               }
               Thread.yield();
           }
        }
    }
    private static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId()!=Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadObjectThread readThread = new ReadObjectThread();
        readThread.start();
        while (true){
            ChangeObjectThread changeThread = new ChangeObjectThread();
            changeThread.start();
            Thread.sleep(150);
            //changeThread.stop();
            changeThread.stopMe();
        }
    }

}
