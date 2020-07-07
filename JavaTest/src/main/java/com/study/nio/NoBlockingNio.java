package com.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class NoBlockingNio {

    @Test
    public void client() throws IOException {

        //获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //切换成非阻塞模式
        sChannel.configureBlocking(false);

        //分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //发送数据给服务端
        buf.put(new Date().toString().getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();

        sChannel.close();

    }

    @Test
    public void server() throws IOException {

        //获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //设置为非阻塞式
        ssChannel.configureBlocking(false);

        //绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //创建选择器
        Selector selector = Selector.open();

        //将通道注册到选择器中 并且指定监听什么事件
        //表示监听ssChannel的accept事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //轮询读取所有准备就绪的channel
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //遍历所有的selectionKey
                //If channel is accepted
                if (selectionKey.isAcceptable()) {
                    //拿到client的channel
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    //将channel注册到selector中
                    sChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = channel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                //每次循环使用的selectionKey都要删掉
                iterator.remove();
            }

        }

    }
}
