package com.study.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BlockingNio {

    @Test
    public void client() throws IOException {

        //获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);

        //分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (inChannel.read(byteBuffer)!=-1){

            //读数据之前要将buffer切换成读数据模式 buffer.flip()
            byteBuffer.flip();
            sChannel.write(byteBuffer);
            //读数据完成之后重置position位置
            byteBuffer.clear();
        }

        //关闭通道
        inChannel.close();
        sChannel.close();

    }

    @Test
    public void server() throws IOException{

        //获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        FileChannel outFileChannel = FileChannel.open(Paths.get("4.jpg"),
                StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        //绑定端口号
        ssChannel.bind(new InetSocketAddress(9898));
        //获取客户端连接的通道
        SocketChannel sChannel = ssChannel.accept();

        //分配一个缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (sChannel.read(buf)!=-1) {
            buf.flip();
            outFileChannel.write(buf);
            buf.clear();
        }
        //关闭通道
        ssChannel.close();
        outFileChannel.close();
        sChannel.close();
    }
}
