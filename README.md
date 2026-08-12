# Spring boot + Spring Cloud + Vue + Element的权限管理系统

| 模块       | 端口   | 功能                                                                 |
|----------|------|--------------------------------------------------------------------|
| admin    | 8000 | 用户、角色、菜单、部门管理功能                                                    |
| backup   | 8001 | 数据库备份功能                                                            |
| monitor  | 8002 | 系统监控功能                                                             |
| consumer | 8003 | 消费者功能                                                              |
| common   | -    | 公共配置和工具类                                                           |
| core     | -    | 核心组件（异常处理、统一返回结果、统一异常处理、统一日志处理、统一权限处理、统一参数处理、统一缓存处理、统一分页处理、统一文件处理） |
| producer | 8006 | 生产者功能                                                              |


# 技术栈

## 后端
| 分类     | 技术                                |
|--------|-----------------------------------|
| 语言     | Java 17                            |
| 框架     | Spring Boot 3.2.5                  |
| 安全     | Spring Security + JWT (jjwt)       |
| 持久层   | MyBatis-Plus + MySQL               |
| 连接池   | Druid                              |
| 会话     | Spring Session JDBC                |
| 验证码   | kaptcha                            |
| 文档     | springdoc-openapi (Swagger)        |
| 导出     | Apache POI (poi-ooxml)             |
| 其他     | Lombok、spring-boot-starter-actuator |

## 前端 (mango-ui)
| 分类     | 技术                                          |
|--------|---------------------------------------------|
| 框架     | Vue 2.5                                      |
| UI 组件  | Element UI 2.15.14                           |
| 路由     | Vue Router 3                                 |
| 状态管理  | Vuex 3.6.2                                   |
| HTTP    | axios                                        |
| 国际化   | vue-i18n                                     |
| 样式     | Sass + 新拟态 (Neumorphism) 设计系统           |
| 图标     | Font Awesome 4.7                             |
| 其他     | js-cookie、mockjs、webpack 3 构建              |

## 部署模块
| 模块       | 说明                       |
|----------|--------------------------|
| admin    | 权限管理核心（用户/角色/菜单/部门）     |
| backup   | 数据库备份                    |
| monitor  | 系统监控                     |
| producer | 生产者                       |
| consumer | 消费者                       |


# 首页图片
![首页](/img/index.png)
# 登录页面
![登录](/img/login.png)

# 监控页面
![监控](/img/monitor.png)
