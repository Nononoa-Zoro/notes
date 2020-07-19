#### Bean的生命周期

1. Bean实例化
2. Bean属性注入
3. 是否实现BeanNameAware接口。是，调用setBeanName()方法。
4. 是否实现BeanFactoryAware接口。是，调用setBeanFactory()方法。
5. 是否实现ApplicationContextAware接口。是，调用setApplicationContext()方法。
6. 是否实现BeanPostProcessor接口，在Bean初始化前回调postProcessBeforeInitialization。
7. 初始化Bean。执行Bean的初始化方法，init-method。
8. 是否实现BeanPostProcessor接口，在Bean初始化前回调postProcessAfterInitialization。
9. 调用Bean的destory()方法销毁Bean。