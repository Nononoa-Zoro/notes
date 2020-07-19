### NIO 零拷贝

![](d:\mdimage\20190925195553.png)

4次状态切换 4次buffer拷贝

![](d:\mdimage\20190925195526.png)

2次状态切换 3次数据拷贝

### 两种重要的系统调用

1. FileChannel.map()

   ```java
   /**
   * Maps a region of this channel's file directly into memory.
   */
   public abstract MappedByteBuffer map(MapMode mode,
                                            long position, long size)
           throws IOException;
   ```

   将文件系统映射到内存中，并且这块内存可以被用户空间（程序）和内核空间同时访问。避免buffer拷贝。

2. FileChannel.transferTo FileChannel.transferFrom

   ```java
   <p> This method is potentially much more efficient than a simple loop
        * that reads from this channel and writes to the target channel.  Many
        * operating systems can transfer bytes directly from the filesystem cache
        * to the target channel without actually copying them.  </p>
          public abstract long transferTo(long position, long count,
                                       WritableByteChannel target)
           throws IOException;
   ```

   FileChannel.transferTo会执行sendFile系统调用，从内核缓冲区直接复制数据到socket缓冲区，这种方式适用于**不需要程序处理数据**的情况。数据直接通过内核写入网络或者磁盘。

   

   #### java Old IO

   ![](F:\mdimage\微信截图_20190925210030.png)

    #### java NIO 内存映射文件（也就是直接缓冲区）

   内存映射文件是在内核空间和用户空间开辟的一块内存区域。这块区域可以让用户与内核同时访问，减少了缓冲区的copy过程。

   ![](F:\mdimage\微信截图_20190925210212.png)

   #### java NIO sendFile系统调用

   ![](F:\mdimage\微信截图_20190925210407.png)

   sendFile直接在内核态将数据复制到socket缓冲区