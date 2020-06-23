#### 更新

```go
//不指定条件，会更新整个表的name字段
db.Model(&User{}).Update("name","hello")
//不指定条件，但是传递的是一个结构体，通过主键定位哪一个记录会被更新
db.Model(&user).Update("name","hello")
```



