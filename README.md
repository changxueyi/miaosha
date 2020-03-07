<<<<<<< HEAD
<<<<<<< HEAD
SpringBoot打造电商基础秒杀项目
项目简介
通过SpringBoot快速建造的前建造分离的电商基础秒杀项目。项目通过应用领域驱动型的分层模型设计方式去完成：用户otp注册，登陆，查看，商品列表，进入商品详情以及倒计时秒杀开始后下单购买的基本流程。

课程地址：SpringBoot建立电商基础秒杀项目

结构图
1.jpg

使用到的外部依赖
org.springframework.boot：spring-boot-starter-web
的mysql：mysql-connector-java
com.alibaba：德鲁伊
org.mybatis.spring.boot：mybatis-spring-boot-starter
org.apache.commons：commons-lang3
org.hibernate：休眠验证器
乔达时间：乔达时间
junit：junit
org.springframework：弹簧测试
org.mybatis.generator：mybatis-generator-maven-plugin（插件）
项目要点
在mybatis-generator.xml配置文件中在对应生成表类名配置中加入 enableCountByExample="false"enableUpdateByExample="false" enableDeleteByExample="false" enableSelectByExample="false"selectByExampleQueryId="false" 避免生成不常用方法
 

前端ajax调用接口获取验证码html / getotp.html，出现跨域请求问题 解决方法：@CrossOrigin(origins = {"*"}, allowCredentials = "true") allowedHeaders允许前端将令牌加入header进行会话共享的跨域请求。allowCredentials授信后，需前端也设置xhfFields授信能力实现跨域会话共享。xhrFields：{withCredentials：true}，
 

统一前端返回格式CommonReturnType {状态：xx，对象：xx} 数据对象->与数据库对应的映射对象模型->使用业务逻辑服务的领域模型对象viewobject->用于前端交互的模型对象
 

使用hibernate-validator通过注解来完成模型参数校验
 

insertSelective 与insert区别：insertSelective与insert区别：insertSelective对应的sql语句加入了NULL校验，即只会插入数据不为null的长度值（null的分区依赖于数据库分区值）插入插入插入所有细分，会插入null。
 

数据库设计规范，设计时长度要设置为非null，并设置默认值，避免唯一索引在null情况下重置等类似场景
 

解决如果事务createorder下单是否回滚，该下单方法中获得流水号id回滚，使等到的id号可能再一次被使用 在genererOrderNo方法前加注解：@Transactional（传播=传播。REQUIRES_NEW）
 

使用聚合模型在itemModel加入PromoModel promoModel，若不为空表示其有未结束的秒杀活动；在orderModel中加入promoId，若不为空，则以秒杀方式下单
待研究问题
2.jpg
=======
# miaosha
这是一个秒杀项目，电商秒杀springboot+redis
>>>>>>> 26fd6504bacca24a95b7eea81a58b18c0c61480c
=======
# miaosha
这是一个秒杀项目，电商秒杀springboot+redis
>>>>>>> 26fd6504bacca24a95b7eea81a58b18c0c61480c
