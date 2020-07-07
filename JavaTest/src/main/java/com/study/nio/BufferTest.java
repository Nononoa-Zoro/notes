package com.study.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Buffer(缓冲区)的四个核心概念
 *
 * capacity:缓冲区的最大容量
 * limit:缓冲区中可以操作得数据大小 在这之后的不可改变
 * position:当前操作得数据的位置
 * mark:记录当前position的位置 便于之后reset回到position的位置
 *
 * 所以 0<=mark<=position<=limit<=capacity
 *
 * 非直接缓冲区 allocate()建立在JVM的内存中
 * 直接缓冲区 allocateDirect()建立在操作系统的物理内存中
 */
public class BufferTest {

    @Test
    public void test1() {

        String str = "abcde";
        //分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("---------allocate()---------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //利用put向缓冲区添加数据
        buffer.put(str.getBytes());
        System.out.println("---------put()---------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //flip()切换到读数据模式
        buffer.flip();
        System.out.println("---------flip()---------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //get()读取数据
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst);
        System.out.println("---------get()---------");
        System.out.println(new String(dst));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //rewind可以重复读取数据
        buffer.rewind();
        System.out.println("---------rewind()---------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //clear清空缓冲区 但是其中的数据仍然有
        buffer.clear();
        System.out.println("---------clear()---------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        System.out.println((char) buffer.get());

    }

    @Test
    public void test2() {

        String str = "abcde";

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(str.getBytes());

        buffer.flip();


        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        //mark可以标记当前position所在的位置 便于 之后的reset重置position位置到现在mark的位置
        buffer.mark();
        buffer.get(dst, 2, 2);
        System.out.println(buffer.position());

        buffer.reset();
        System.out.println(buffer.position());

        //判断buffer中是否还有可以操作得数据 如果有看看还剩几个
        if(buffer.hasRemaining()){
            System.out.println(buffer.remaining());
        }
    }
}
