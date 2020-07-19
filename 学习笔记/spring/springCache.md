### SpringCache

```java
    /**
     * 将方法的运行结果进行缓存，以后在要相同的数据就直接从缓存中获取，不用调用方法
     *
     * cacheManager管理多个cache组件，对缓存真正的CRUD操作是在Cache组件中，每一个缓存组件都有自己唯一的名字
     * 几个属性 ：
     *      cacheNames/value:指定缓存的名字
     *      key:缓存数据使用的key。默认key是方法参数的值，value是方法的返回值(支持SpEl)
     *      #id:参数id的值 #a0 #p0 #root.args[0]
     *      keyGenerator: key的生成器 指定自己的key的生成方式
     *      key 和 keyGenerator 二选一
     *      cacheManager：指定缓存管理器；或者cacheResolver
     *      cacheManager和cacheResolver二选一
     *      condition:指定符合条件的情况下才缓存
     *      unless:否定缓存 当这个条件满足时 方法的返回值就不会缓存
     *      sync:是否使用异步模式
     *
     * 原理：
     *      1.自动配置类CacheAutoConfiguration
     * @param id
     * @return
     */
//  @Cacheable(cacheNames = "emp",key = "#id",condition = "#id>0",unless ="#result==null")
    @Cacheable(cacheNames = {"emp"})
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        Employee emp = employeeMapper.getEmp(id);
        return emp;
    }
```

默认导入一下自动配置类

![1564990189396](F:\mdimage\1564990189396.png)

但是其实默认只使用了SimpleCacheConfiguration

```java
@Configuration
@ConditionalOnMissingBean(CacheManager.class)
@Conditional(CacheCondition.class)
class SimpleCacheConfiguration {

	private final CacheProperties cacheProperties;

	private final CacheManagerCustomizers customizerInvoker;

	SimpleCacheConfiguration(CacheProperties cacheProperties, CacheManagerCustomizers customizerInvoker) {
		this.cacheProperties = cacheProperties;
		this.customizerInvoker = customizerInvoker;
	}

	@Bean
	public ConcurrentMapCacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		List<String> cacheNames = this.cacheProperties.getCacheNames();
		if (!cacheNames.isEmpty()) {
			cacheManager.setCacheNames(cacheNames);
		}
		return this.customizerInvoker.customize(cacheManager);
	}

}

```

