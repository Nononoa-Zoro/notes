## IN

不依赖外表的查询叫做 **非相关子查询**

特点：先查询内表，再查询外表。

in会首先执行子查询，查询到数据之后和外表做笛卡尔积，然后根据in的条件过滤掉不匹配的数据。

所以，in关键字适合于子查询数据较小的情况。子查询过滤条件好的情况。



not in : 内外表都用到全表扫描

not exists: 内表还可以用到索引

## EXISTS

内表依赖外表叫做 **相关子查询**

关联子查询：

特点：先查询外表，再查询内表。

循环遍历外表，检查外表中的数据是否有和内表中的数据一样的。如果有就返回。

所以EXISTS适用于外表较小的情况。外表过滤条件好的情况。

