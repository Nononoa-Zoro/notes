package com.study.cocurrent.future;

public class RealData implements Data {

    protected final String result;

    //模拟真实准备数据需要耗时的过程
    public RealData(String para) throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(para);
            Thread.sleep(100);
        }
        result = stringBuffer.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
