#### Java Annotation

是指在程序的编译，类加载，运行时可以被读取的特殊标记。通过Annotation可以帮助程序开发人员在不改变程序逻辑的基础上在源文件插入一些补充的逻辑。



#### Annotation的生命周期

1. RetentionPolicy.**SOURCE** ：注解只保留在源文件中，编译的时候就会丢弃。

2. RetentionPolicy.**CLASS** ：这是默认的生命周期，注解被保留到class文件中，但是JVM加载Class文件的时候就会丢弃。
3. RetentionPolicy.**RUNTIME** ：不仅在JVM加载的时候存在，而且程序运行时仍然存在。

#### SOURCE<CLASS<RUNTIME





#### @Target

@Target说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。

![](D:\mdimage\微信截图_20200314130748.png)

####@Retention 

**@Retention**定义了该Annotation被保留的时间长短 。

SOURCE：在源码中保留，编译的时候丢弃。

CLASS：编译的时候保留，JVM类加载的时候丢弃。

RUNTIME：运行时保留。

#### @Documented

是否需要文档化

####@Inherited

　　@Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是可以被继承的。即被这个注解标记的类的子类也可以继承这个类。