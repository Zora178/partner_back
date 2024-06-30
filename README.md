# 搭子平台
## 技术栈
1. Java 编程语言 + SpringBoot 框架
2. SpringMVC + MyBatis + MyBatis Plus（提高开发效率）
3. MySQL 数据库
4. Swagger + Knife4j 接口文档

## 数据库
### 标签
性别：男、女

方向：Java、C++、Go、前端

正在学：Spring

目标：考研、春招、秋招、社招、考公、竞赛（蓝桥杯）、转行、跳槽

段位：初级、中级、高级、王者

身份：小学、初中、高中、大一、大二、大三、大四、学生、待业、已就业、研一、研二、研三

状态：乐观、有点丧、一般、单身、已婚、有对象
### 字段
字段：

id int 主键

标签名 varchar 非空（必须唯一，唯一索引）

上传标签的用户 userId int（如果要根据 userId 查已上传标签的话，最好加上，普通索引）

父标签 id ，parentId，int（分类）

是否为父标签 isParent, tinyint（0 不是父标签、1 - 父标签）

创建时间 createTime，datetime

更新时间 updateTime，datetime

是否删除 isDelete， tinyint（0、1）

### Swagger 原理：

1. 引入依赖（Swagger 或 Knife4j：https://doc.xiaominfo.com/knife4j/documentation/get_start.html）
2. 自定义 Swagger 配置类
3. 定义需要生成接口文档的代码位置（Controller）
4. 千万注意：线上环境不要把接口暴露出去！！！可以通过在 SwaggerConfig 配置文件开头加上 `@Profile({"dev", "test"})` 限定配置仅在部分环境开启
5. 启动即可
6. 可以通过在 controller 方法上添加 @Api、@ApiImplicitParam(name = "name",value = "姓名",required = true)    @ApiOperation(value = "向客人问好") 等注解来自定义生成的接口描述信息
适用于前后端分离项目

(springboot2.6以上需要修改配置mvc使用matching-strategy: ant_path_matcher)

网页访问
http://localhost:8080/api/doc.html#/home

测试（注意profile的设置一致性）
1、userLogin登录  zora 12345678
2、getCurrent获取当前登录用户 

redis(单机改分布式)