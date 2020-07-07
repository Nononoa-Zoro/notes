package com.study.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 使用通道将标准输入的数据写到标准输出中
 * channel.read(buf) 从通道中读取数据到buffer中
 * channel.write(buf) 将buffer中的数据写到通道中
 */
public class ChannelCopy {

    public static void main(String[] args) throws IOException{

        ReadableByteChannel source = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);

        ByteBuffer buffer = ByteBuffer.allocate(16 * 1024);

        while (source.read(buffer)!=-1){
            buffer.flip();
            while (buffer.hasRemaining()){
                dest.write(buffer);
            }
            buffer.clear();
        }

        source.close();
        dest.close();
    }

}
