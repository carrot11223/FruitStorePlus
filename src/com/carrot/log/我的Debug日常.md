# 前言

>  每个程序员或许都有一点小小的英雄梦，最近我就做了个梦。

由于前段时间学一些框架的东西，感觉遇到了瓶颈期，寻思着“万丈高楼平地起”，要不把基础拿出来练练手? 的确，我是这么做了，但是过程中并不是那么愉快，感觉以前很多东西都忘记了，包括一些编程的思想啊，比如虽然说后面的会出现很多的数据库框架，点一点就好了，但是我对于jdbc并不是那么熟悉，所以这次也是手写了一下jdbc的封装，前端的话也是手写了一下，稍微照着文档看了看，也遇到了很多问题，但最终都解决了。这两天来，感觉自己因为学了后面的东西，再看前面的东西和我第一次黑人问号脸的阶段完全不一样，虽然总是骂骂咧咧一些例如“ 404我与你不共戴天！”的戏谑，但是当解决一个bug的时候感觉还是挺不错的，这两天感觉写代码的时间并没有占多少，就是调bug打断点需要好久。

# 技术选型

前端：html + css + js + thymeleaf

后端：javaweb + mysql + servlet

其实都是最基础的东西，不过对于以后框架上出的问题非常有帮助。

# Debug经过

* 第一次是什么问题来着，哦对，困住我时间最长的，也是最简单的麻烦，由于我修改完代码，但是没有手动重新编译和部署（idea有时候就会有这个问题），Artifacts模块就一直是之前的代码，好吧，是我和你不熟。

* 第二次，由于从数据库里面查出来的东西需要映射到pojo类当中去，但是一直是null，我就纳闷了，debug的时候看到已经连接了数据库啊，而且查出来了有多少条记录，怎么一行数据最后都是空了呀，最后我发现我自己创建数据库里面的数据类型和封装类里面的数据类型不一致，导致拿不到数据。

* 第三次啊，没有去maven仓库拿jar包，随便在网上找了个jar包导进去，发现老是缺一个jar里面的模块，我就找了一下那个模块，但是依旧报错，我就把thymeleaf相关的jar全都删干净，乖乖去仓库里面找了，结果马上就好了。

* 第四次，是我已经写好了后端大部分东西，把前端一些页面给导入进来了，但是很奇怪啊，样式和js都不显示，路径没问题啊，页面也没有任何报错啊，而且百度不到这种错误，我就先放着没管，有一次我把项目默认访问的路径给改成/index ，发现就出现了样式，我之前默认路径是/，而且也写了相关的模块，也成功跳转了，就非常奇怪。

* 第五次，不知道怎么回事，突然版本就冲突了，好吧，重新导入包。

* 第六次，我写的分页查询，愣是页面怎么点都不起作用，没有报错，后来仔细看了一下，我没有写disable=“true” 当中的true的另一半表达式，所以按钮默认都是不能点的，但是没有什么异样，很难看出来。

* 第七次，分页查询的pageNo第一次写错了，导致出现了负数。

* 第八次，@{}嵌入到{}里面，少写了一个{}

* 第九次，手写查总行数的时候，声明错了变量。

* 第十次，页面一直是缓存，war包迟迟不更新，只好清理缓存，clear+rebuild ，虽然也不管用，但是最后就自己好了，我也不知道为什么。

  ......

  当然还遇到了非常多的错误，唉，默默哭泣....>@<

# 感悟

一个系统，甚至是那些很小的练手系统，都是有很多的逻辑漏洞在里面的，正好最近在看渗透测试，会发现这种思维会多多少少渗透到对于网站的开发里面的，我觉得这二者是相辅相成的，当然我说的是偏向于web漏洞测试的，希望可以再接再厉，把我同学的网站拿来练练手哈哈，当然也可以是自己的。

最近重温who am i ，真正理解人才是最大的漏洞这句话，说点题外话，感觉很多时候社会工程学比搞技术的更高级呢.....

see you ,=_=!