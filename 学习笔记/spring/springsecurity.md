```java
UsernamePasswordAuthenticationFilter

DaoAuthenticationProvider	
```

![](D:\mdimage\微信截图_20200108102819.png)

```java
//DaoAuthenticationProvider class
UserDetails loadedUser = this.getUserDetailsService().loadUserByUsername(username);

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

provider通过UserDetailsService的loadUserByUsername获取账号和密码信息
```

