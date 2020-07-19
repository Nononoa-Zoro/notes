#### CONCAT

 CONCAT(str1,str2,…)      

返回结果为连接参数产生的字符串。如有任何一个参数为NULL ，则返回值为 NULL。 

####CONCAT_WS

CONCAT_WS() 代表 CONCAT With Separator ，是CONCAT()的特殊形式。   第一个参数是其它参数的分隔符。分隔符的位置放在要连接的两个字符串之间。分隔符可以是一个字符串，也可以是其它参数。如果分隔符为 NULL，则结果为 NULL。函数会忽略任何分隔符参数后的 NULL 值。 


