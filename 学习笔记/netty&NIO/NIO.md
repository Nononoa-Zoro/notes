###NIO和IO的区别
- IO：面向流，阻塞IO
Java IO面向流意味着每次从流中读一个或多个字节，直至读取所有字节，它们没有被缓存在任何地方。
- NIO：面向缓冲，非阻塞IO，引入了selector
Java NIO的缓冲导向方法略有不同。数据读取到一个它稍后处理的缓冲区，需要时可在缓冲区中前后移动。这就增加了处理过程中的灵活性。

###阻塞与非阻塞IO
- 阻塞式IO：当一个线程调用read() 或 write()时，该线程被阻塞，直到有一些数据被读取，或数据完全写入。
- 非阻塞式IO：从通道请求数据的时候，如果通道没有数据，该线程不会阻塞，它可以干其他的事。同理线程往通道写数据也是这样，不需要等他完全写入，这个线程可以干其他事。

###选择器
Java NIO的选择器允许一个单独的线程来监视多个输入通道，你可以注册多个通道使用一个选择器，然后使用一个单独的线程来“选择”通道：这些通道里已经有可以处理的输入，或者选择已准备写入的通道。这种选择机制，使得一个单独的线程很容易来管理多个通道。 



### Buffer

###### ![](D:\mdimage\944365-93cd55b2ed7cd37c.png)

- 重要概念

  1. mark (当调用mark方法时会将当前position的值保存在mark中，在之后调用reset方法时可以将position值恢复为之前的mark中保存的值)
  2. position（下一个将要读或是写的元素的索引位置）
  3. limit（第一个无法读写的元素的索引位置）
  4. capacity（buffer的最大容量一旦定义将无法改变）

- 重要方法

  1. mark():标记 记录当前position的值，并将之保存到mark变量中去。

  2. flip(): flip之后才可以开始读取数据

     ```java
        public final Buffer flip() {
             limit = position;
             position = 0;
             mark = -1;
             return this;
         }
     ```

  3. clear() 恢复到原始状态

     ```java
         public final Buffer clear() {
             position = 0;
             limit = capacity;
             mark = -1;
             return this;
         }
     ```

  4. rewind() 可以再次读取数据

     ```java
        public final Buffer rewind() {
             position = 0;
             mark = -1;
             return this;
         }
     ```

- ByteBuffer的常见实现类

  1. HeapByteBUffer 在JVM堆上分配内存
  2. HeapByteBufferR 同上 且不允许修改 只读

- 直接缓冲区（**DirectByteBuffer**)

  JVM中的内存缓冲区(DirectByteBuffer), 通过address (JNI 调用的OS的内存地址)访问物理内存。

  ```java
  public abstract class{    
  	// Used only by direct buffers
      // NOTE: hoisted here for speed in JNI GetDirectBufferAddress
      long address;
  }
  ```

  ​	**DirectByteBuffer**是JVM堆上内存分配的，**address**是OS中的物理内存地址。**DirectByteBuffer**通过address的内存地址直接与OS交互。

  ​	与之对应的是**HeapByteBuffer**，OS处理程序数据时不会直接在JVM内存中去处理。它会拷贝JVM的数据到OS的物理内存缓冲区中，然后与物理磁盘交互。

  

  - ***为什么OS不直接操作JVM中的内存区域？***

    ​	JVM中的堆内存空间随时都有可能发生GC，GC的过程会导致JVM内存中变量地址变化。（标记，压缩，移动）。

    ​	所以采用非直接缓冲区（HeapByeBuffer）时，OS会首先拷贝JVM中的数据到OS的物理内存中，然后与IO交互。但是从JVM拷贝数据是很快的，但是IO处理数据大部分时间都会阻塞，所以这样的效率是很低的。

    

    ### **scatter&gather**

    channel可以读取多个buffer，同时也可以向多个buffer中写入数据。

    

    ### Socket编程

    ```java
    //server
    ServerSocket serverSocket = ...
    serverSocket.bind(8899);
    while(true){
       Socket socket = serverSocket.accept();//阻塞方法一直等待客户端连接
        new Thread(){
            run(){
                socket.getInpuStream();
            }
        }.start();
    }
    
    //client
    Socket socket = new Socket("localhost",8899);
    socket.connect();
    
    //8899端口号只是数据建立连接使用的端口号，服务端具体处理数据和写给客户端的端口号是server随机选择的。
    ```

    

    

    

    

    

    

    

  