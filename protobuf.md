### protobuf的Encoding规则

基于proto3语法

```protobuf
message Test1 {
  optional int32 a = 1;
}
```

以官方的为例：

假设你传递的a的值是150，那么Encoding之后的值是08 96 01.

**怎么得到这个值的？**

a字段的key是1，（key<<3）| type 可以得到tag的值。根据下表可以查出int32的type是0，1000|0=1000，对应的16进制就是08。0表示这是最后一个字节。

![](F:\notes\image\企业微信截图_20200612144446.png)

150转换为二进制10010110，取后面七位0010110作为第一个字节的后七位，如果当前字节不是最后一个字节那么最高位为1，所以第一个字节的值就是1001 0110对应的16进制数就是96。第二个字节最低位为1，由于这是最后一个字节，所以最高位用0表示，第二个字节为0000 00001对应的16进制数是01。这就是value的值（96 01）

**注意**：使用protobuf协议传递数据的时候，都是以<tag><value>的形式紧凑连接在一起的。



#### field number

每一个在message中定义的字段（field）都有一个唯一的数字标识。

**一旦这个message type定义投入使用这个数字就不能改变**

field number 从1到15的字段都采用1个字节编码，16到2047的采用两个字节编码。最大可用的field number是2^29-1。



#### Reserved Field

如果你希望之后版本的proto不再使用当前版本的某些字段，可以使用reserved标识。protobuf 编译器会告诉用户在未来的proto定义中不再使用这个字段。



#### Default Value

如果被编码的message的某个field没有设置一个指定的值，那么不同的类型会自动设置一个默认的值。



#### Enum（枚举类型）

```protobuf
enum Corpus {
    UNIVERSAL = 0;//一定要定义一个默认值为0，0需要在第一个位置，这是为了和proto2兼容
    WEB = 1;
    IMAGES = 2;
    LOCAL = 3;
    NEWS = 4;
    PRODUCTS = 5;
    VIDEO = 6;
}
enum EnumAllowingAlias {
    option allow_alias = true;//可以设置同一个value对应不同的enum字段
    UNKNOWN = 0;
    STARTED = 1;
    RUNNING = 1;
}
```



#### 嵌套类型

```protobuf
message SearchResponse {
  message Result {
    string url = 1;
    string title = 2;
    repeated string snippets = 3;
  }
  repeated Result results = 1;
}

//如果你希望用到SearchResponse内部定义的message，那么你应该使用parent.child的形式
message SomeOtherMessage {
  SearchResponse.Result result = 1;
}
```



#### 更新一个message的注意事项

1. 不要更改任何现有字段的数值标识。
2. 非required字段可以移除，只要他们的标识在新的消息类型中不再使用，你可以使用“ "OBSOLETE_"”或者“reserved”来标识。



