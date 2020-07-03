# -TOP250
爬取了中文名、外国名、其他名字以及豆瓣链接
（中间有个地方搞复杂了懒得改了作业没做完，其实可以优化下各个结构间关系）

## 相关函数

|函数名|参数|返回值|说明|
|---|---|---|---|
|get_movies|页数|list|获取所有response中电影的关键信息|
|get_fname|list|list|获取电影的中英文名|
|get_oname|list|list|获取电影的其他别名|
|get_url|list|list|获取电影的豆瓣链接|

## 效果图

![](https://i.imgur.com/hjz5LOG.png)