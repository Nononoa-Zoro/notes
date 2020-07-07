package com.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 管道 pipe
 */
public class TestPipe {
    @Test
    public void test() throws IOException {

        Pipe pipe = Pipe.open();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("单向管道发送数据".getBytes());

        //向管道写入数据
        Pipe.SinkChannel sink = pipe.sink();
        buf.flip();
        sink.write(buf);

        //从管道读取数据
        Pipe.SourceChannel source = pipe.source();
        buf.flip();
        int len = source.read(buf);
        System.out.println(new String(buf.array(),0,len));

        sink.close();
        source.close();

    }
}
