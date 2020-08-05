##实训项目
房屋租赁系统
===========================
  
###项目简介  
######开发目的  
为了规范租房市场、方便房东和租客完成订单交易、管理房屋，我们小组开发了一个房屋租赁系统来管理成都市的租房市场  
######受众人群  
- 手头有空房、想要出租的房东  

- 交易记录太多、不想被数据处理烦恼的人群  

###服务器端环境依赖以及所需软件
1. Java 1.8
1. Tomcat 8.x
1. MySQL 8.X
1. IDEA 2019+
  
###使用框架
Maven - 依赖属性管理
  
###部署步骤
1.分别添加Java、Tomcat、MySQL的系统环境变量
2.添加Tomcat至IDEA
3.运行IDEA
  
###目录结构描述
<pre>
│  .gitignore
│  pom.xml
│  README.md
│  
├─.idea
│  │  .gitignore
│  │  compiler.xml
│  │  encodings.xml
│  │  jarRepositories.xml
│  │  misc.xml
│  │  practiceTraining.iml
│  │  uiDesigner.xml
│  │  vcs.xml
│  │  webContexts.xml
│  │  workspace.xml
│  │  暑期实训.iml
│  │  
│  ├─libraries
│  │      javax_mail_1_5_6.xml
│  │      
│  └─shelf
│      │  Uncommitted_changes_before_Update_at_2020_7_17_17_10__Default_Changelist_.xml
│      │  
│      └─Uncommitted_changes_before_Update_at_2020_7_17_17_10_[Default_Changelist]
│              EmailUtils$1.class
│              EmailUtils$11.class
│              EmailUtils.class
│              EmailUtils1.class
│              shelved.patch
│              
├─src
│  └─main
│      ├─java
│      │  └─com	- 项目路径
│      │      └─renhouse - 后端
│      │          ├─dao - dao层
│      │          │  │  BaseDao.java
│      │          │  │  EmailDao.java
│      │          │  │  HouseDao.java
│      │          │  │  OrderDao.java
│      │          │  │  TenantDao.java
│      │          │  │  UserDao.java
│      │          │  │  
│      │          │  └─impl - dao层的实现
│      │          │          EmailDaoImpl.java
│      │          │          HouseDaoImpl.java
│      │          │          OrderDaoImpl.java
│      │          │          TenantDaoImpl.java
│      │          │          UserDaoImpl.java
│      │          │          
│      │          ├─filter - 过滤器
│      │          │      GlobalFilter.java
│      │          │      
│      │          ├─junit - 各类实现的测试
│      │          │  ├─dao
│      │          │  │      EmailDaoImplTest.java
│      │          │  │      HouseDaoImplTest.java
│      │          │  │      OrderDaoImplTest.java
│      │          │  │      TenantDaoImplTest.java
│      │          │  │      UserDaoImplTest.java
│      │          │  │      
│      │          │  ├─service	
│      │          │  │      BillServiceImplTest.java
│      │          │  │      HouseServiceImplTest.java
│      │          │  │      OrderServiceImplTest.java
│      │          │  │      UserServiceImplTest.java
│      │          │  │      UserServiceTest.java
│      │          │  │      
│      │          │  └─utils
│      │          │          EmailUtilsTest.java
│      │          │          JdbcUtilsTest.java
│      │          │          TimeUtilsTest.java
│      │          │          
│      │          ├─lisentner - 监听器
│      │          │      OnlineNumberListener.java
│      │          │      
│      │          ├─pojo - 数据库对象
│      │          │  │  Email.java
│      │          │  │  House.java
│      │          │  │  Order.java
│      │          │  │  Page.java
│      │          │  │  Tenant.java
│      │          │  │  User.java
│      │          │  │  
│      │          │  └─vo - VO对象
│      │          │          Bill.java
│      │          │          DetailBill.java
│      │          │          HouseStatus.java
│      │          │          NearDateHouse.java
│      │          │          TenantMaintenanceFee.java
│      │          │          
│      │          ├─service	- service对象及实现
│      │          │  │  BillService.java
│      │          │  │  HouseService.java
│      │          │  │  OrderService.java
│      │          │  │  UserService.java
│      │          │  │  
│      │          │  └─impl
│      │          │          BillServiceImpl.java
│      │          │          HouseServiceImpl.java
│      │          │          OrderServiceImpl.java
│      │          │          UserServiceImpl.java
│      │          │          
│      │          ├─servlet	- Servlet程序
│      │          │      BaseServlet.java
│      │          │      BillServlet.java
│      │          │      HouseServlet.java
│      │          │      OrderServlet.java
│      │          │      UploadServlet.java
│      │          │      UserServlet.java
│      │          │      UtilsServlet.java
│      │          │      
│      │          └─utils - 工具类
│      │                  AESUtils.java
│      │                  CookieUtils.java
│      │                  EmailUtils.java
│      │                  JdbcUtils.java
│      │                  MD5Utils.java
│      │                  TimeUtils.java
│      │                  WebUtils.java
│      │                  
│      ├─resources - 服务器资源库
│      │      jdbc.properties - JDBC连接配置
│      │      
│      └─webapp	- 前端
│          │  index.jsp
│          │  
│          ├─pages - 前端页面
│          │  │  home.jsp
│          │  │  pleaselogin.jsp
│          │  │  
│          │  ├─common - 常规页
│          │  │      base.jsp
│          │  │      head.jsp
│          │  │      
│          │  ├─function - 功能界面
│          │  │      feeManange.jsp
│          │  │      housemanage.jsp
│          │  │      leaseExpiryInfo.jsp
│          │  │      main.jsp
│          │  │      myBill.jsp
│          │  │      ordermanage.jsp
│          │  │      rentedHouseManage.jsp
│          │  │      unRentedHouseInfo.jsp
│          │  │      
│          │  └─user - 用户登录和注册页面
│          │          login.jsp
│          │          register.jsp
│          │          
│          ├─static	- 静态页面元素以及样式布局
│          │  ├─css	- CSS样式排版
│          │  │      chat.css
│          │  │      demo.css
│          │  │      iconfont.css
│          │  │      
│          │  ├─images - 网页源图像
│          │  │      bg.jpg
│          │  │      bqxtb01.png
│          │  │      face.jpg
│          │  │      home-banner.jpg
│          │  │      icon01.png
│          │  │      icon02.png
│          │  │      icon03.png
│          │  │      orders.svg
│          │  │      orders_finish.svg
│          │  │      orders_not_finish.svg
│          │  │      shop.svg
│          │  │      
│          │  ├─layui - 设计table、布局和表单
│          │  │  │  layui.all.js
│          │  │  │  layui.js
│          │  │  │  
│          │  │  ├─css - 布局样式
│          │  │  │  │  layui.css
│          │  │  │  │  layui.mobile.css
│          │  │  │  │  
│          │  │  │  └─modules
│          │  │  │      │  code.css
│          │  │  │      │  
│          │  │  │      ├─laydate
│          │  │  │      │  └─default
│          │  │  │      │          laydate.css
│          │  │  │      │          
│          │  │  │      └─layer
│          │  │  │          └─default
│          │  │  │                  icon-ext.png
│          │  │  │                  icon.png
│          │  │  │                  layer.css
│          │  │  │                  loading-0.gif
│          │  │  │                  loading-1.gif
│          │  │  │                  loading-2.gif
│          │  │  │                  
│          │  │  ├─font - 字体
│          │  │  │      iconfont.eot
│          │  │  │      iconfont.svg
│          │  │  │      iconfont.ttf
│          │  │  │      iconfont.woff
│          │  │  │      iconfont.woff2
│          │  │  │      
│          │  │  ├─images - 源图像
│          │  │  │  └─face
│          │  │  │          0.gif
│          │  │  │          1.gif
│          │  │  │          10.gif
│          │  │  │          11.gif
│          │  │  │          12.gif
│          │  │  │          13.gif
│          │  │  │          14.gif
│          │  │  │          15.gif
│          │  │  │          16.gif
│          │  │  │          17.gif
│          │  │  │          18.gif
│          │  │  │          19.gif
│          │  │  │          2.gif
│          │  │  │          20.gif
│          │  │  │          21.gif
│          │  │  │          22.gif
│          │  │  │          23.gif
│          │  │  │          24.gif
│          │  │  │          25.gif
│          │  │  │          26.gif
│          │  │  │          27.gif
│          │  │  │          28.gif
│          │  │  │          29.gif
│          │  │  │          3.gif
│          │  │  │          30.gif
│          │  │  │          31.gif
│          │  │  │          32.gif
│          │  │  │          33.gif
│          │  │  │          34.gif
│          │  │  │          35.gif
│          │  │  │          36.gif
│          │  │  │          37.gif
│          │  │  │          38.gif
│          │  │  │          39.gif
│          │  │  │          4.gif
│          │  │  │          40.gif
│          │  │  │          41.gif
│          │  │  │          42.gif
│          │  │  │          43.gif
│          │  │  │          44.gif
│          │  │  │          45.gif
│          │  │  │          46.gif
│          │  │  │          47.gif
│          │  │  │          48.gif
│          │  │  │          49.gif
│          │  │  │          5.gif
│          │  │  │          50.gif
│          │  │  │          51.gif
│          │  │  │          52.gif
│          │  │  │          53.gif
│          │  │  │          54.gif
│          │  │  │          55.gif
│          │  │  │          56.gif
│          │  │  │          57.gif
│          │  │  │          58.gif
│          │  │  │          59.gif
│          │  │  │          6.gif
│          │  │  │          60.gif
│          │  │  │          61.gif
│          │  │  │          62.gif
│          │  │  │          63.gif
│          │  │  │          64.gif
│          │  │  │          65.gif
│          │  │  │          66.gif
│          │  │  │          67.gif
│          │  │  │          68.gif
│          │  │  │          69.gif
│          │  │  │          7.gif
│          │  │  │          70.gif
│          │  │  │          71.gif
│          │  │  │          8.gif
│          │  │  │          9.gif
│          │  │  │          
│          │  │  └─lay - 样式脚本
│          │  │      └─modules
│          │  │              carousel.js
│          │  │              code.js
│          │  │              colorpicker.js
│          │  │              element.js
│          │  │              flow.js
│          │  │              form.js
│          │  │              jquery.js
│          │  │              laydate.js
│          │  │              layedit.js
│          │  │              layer.js
│          │  │              laypage.js
│          │  │              laytpl.js
│          │  │              mobile.js
│          │  │              rate.js
│          │  │              slider.js
│          │  │              table.js
│          │  │              transfer.js
│          │  │              tree.js
│          │  │              upload.js
│          │  │              util.js
│          │  │              
│          │  ├─other - 其他
│          │  │      iconfont.eot
│          │  │      iconfont.ttf
│          │  │      iconfont.woff
│          │  │      kefu.html
│          │  │      
│          │  └─script - 脚本
│          │          canvas-nest.min.js
│          │          echarts.min.js
│          │          index.js
│          │          jquery-3.4.1.min.js
│          │          md5.js
│          │          notice.js
│          │          onlineService.js
│          │          sweetalert.min.js
│          │          
│          └─WEB-INF - Servlet部署设置
│                  web.xml
│                  
└─target - 其他文件
    ├─generated-sources
    │  └─annotations
    └─practicalTraining
            UploadTest.jsp
</pre>              
###关于作者
小组：Beatbit  
组长：杨文豪  
成员：闵海、吉天昊、王晨霖、秦文杰、夏新宇  
  
###更新日志



