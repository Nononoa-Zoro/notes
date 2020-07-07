package com.study.cocurrent.future;

public class Client {
    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread() {
            @Override
            public void run() {

                try {
                    //构造真实数据 这是一个耗时的过程
                    RealData realData = new RealData(queryStr);
                    futureData.setRealData(realData);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
        return futureData;
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        Thread.sleep(2000);
        //client请求真实数据 data是一个伪造的数据 data.getResult只有在准备好数据时才会返回 有点像懒加载机制
        System.out.println("数据="+data.getResult());
    }
}
