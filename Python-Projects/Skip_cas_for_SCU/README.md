# 绕过某双一流验证码输入的一种简单方式
学校采用了Cas认证，我水平还很菜不能完全用requests模拟出这个过程，就想到了用Selenium通过浏览器的无窗口化操作实现   

**声明：本页所有内容仅供学习和熟悉Selenium模块以及Cookies使用，任何使用本教程所导致的违法行为，与作者无关**
## 相关函数

|函数名|参数|返回值|说明|
|---|---|---|---|
|csh_wuchuangkou|None|WebDriver|无窗口化启动Chrome|
|csh_youchuangkou|None|WebDriver|有窗口化启动Chrome|
|cookie_get|None|cookie|获取网站Cookie|


## 效果图

![](https://i.imgur.com/QNp8zoK.png)
