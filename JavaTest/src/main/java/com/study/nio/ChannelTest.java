package com.study.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelTest {

    //编码和解码
    @Test
    public void test5() throws CharacterCodingException {
        Charset gbk = Charset.forName("GBK");
        CharsetEncoder encoder = gbk.newEncoder();
        CharsetDecoder decoder = gbk.newDecoder();

        CharBuffer cBuf = CharBuffer.allocate(1024);

        cBuf.put("曾豪");
        cBuf.flip();

        //编码 字符串--->字节数组
        ByteBuffer bBuf = encoder.encode(cBuf);
        bBuf.flip();
        CharBuffer ch = decoder.decode(bBuf);
        System.out.println(ch.toString());


    }

    //分散和聚集
    @Test
    public void test4() throws IOException {

        //分散读取
        RandomAccessFile inFile = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = inFile.getChannel();

        //两个通道
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buf = {buffer1, buffer2};
        //将两个通道的数据读到channel中
        channel.read(buf);

        //读取之前一定要flip 切换成读取模式
        for (ByteBuffer buffer : buf) {
            buffer.flip();
        }
        System.out.println(new String(buf[0].array(), 0, buf[0].limit()));
        System.out.println("--------------");
        System.out.println(new String(buf[1].array(), 0, buf[1].limit()));

        //聚集写入
        RandomAccessFile outFile = new RandomAccessFile("2.txt", "rw");
        FileChannel outFileChannel = outFile.getChannel();

        outFileChannel.write(buf);

    }


    //使用transferTo 在通道之间传输数据(非直接缓冲区)
    @Test
    public void test3() throws
            IOException {

        FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();

    }

    @Test
    public void test2() {

        //使用直接缓冲区 内存映射文件
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            //内存映射文件
            MappedByteBuffer inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

            byte[] dst = new byte[inMapBuffer.limit()];
            inMapBuffer.get(dst);

            outMapBuffer.put(dst);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test1() throws IOException {

        //输入和输出是相对于这个程序的
        FileInputStream in = new FileInputStream("1.jpg");
        FileOutputStream out = new FileOutputStream("2.jpg");

        FileChannel inChannel = in.getChannel();
        FileChannel outChannel = out.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (inChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        in.close();
        out.close();
        inChannel.close();
        outChannel.close();
    }
}
