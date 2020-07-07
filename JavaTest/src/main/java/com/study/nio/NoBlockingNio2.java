package com.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class NoBlockingNio2 {

    @Test
    public void send() throws IOException {
        //UDP
        DatagramChannel dc = DatagramChannel.open();
        dc.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.next();
            buf.put((new Date().toString()+"\t"+s).getBytes());
            buf.flip();
            dc.send(buf,new InetSocketAddress("127.0.0.1",9899));
            buf.clear();
        }
        dc.close();
    }

    @Test
    public void receive() throws IOException {
        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        dc.bind(new InetSocketAddress(9899));

        Selector selector = Selector.open();

        dc.register(selector,SelectionKey.OP_READ);

        while (selector.select()>0){

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                if(key.isReadable()){
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //UDP是无连接的 所以不需要accept数据
                    dc.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(),0,buffer.limit()));
                }
            }
            iterator.remove();
        }


    }
}
