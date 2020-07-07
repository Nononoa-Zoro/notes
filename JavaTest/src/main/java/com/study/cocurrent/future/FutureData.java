package com.study.cocurrent.future;

public class FutureData implements Data {

    //真实数据
    protected RealData realData=null;
    //表示已经准备好数据
    protected boolean isReady=false;

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData=realData;
        isReady=true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady){
            //没准备好 却在请求数据 就一直阻塞
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        return realData.result;
    }
}
