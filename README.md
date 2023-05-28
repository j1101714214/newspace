## README

> Newspace是一个适用于年轻人的社交平台, 不仅仅致力于年轻人之间的简单社交活动, 而是借助资源推送, 好友结伴以及相关度分配为年轻人建立有共同爱好的小团体, 
> 不仅如此, 随着年轻群体之中社交恐惧症人员的比例上涨, 社交平台将为这一部分人群补缺社交活动的空白!

+ 附注: 项目技术栈: Mysql + MyBatis-Plus + Vue

### 登录注册模块
> 采用Spring Security配合JWT验证实现初步的登录注册检验以及用户角色的分发, 对于不同的方法设定访问权限. 对于登录注册这一部分的实现已经完成, 但是对于
> 规则的指定需要更详细的设计, 仅仅是为了大作业而简单实现部分

### 用户信息管理模块
> 基础的增删查改及其接口已基本实现. 现在所暴露出来的问题有数据缓存 <- redis以提高数据的并发量. 后续需要大力优化

### 博客内容管理模块
> 博客基本的CRUD操作已经基本实现, 但是吞吐量极低需要采取热点数据优化. 关于定时任务, 属于中道崩殂. 需要后续继续完善(也许可以试试Quartz!!, xxl对于这破东西貌似太大了?)

### 社交平台服务模块
> 待开发... (开新坑懂得都懂)