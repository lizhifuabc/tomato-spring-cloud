# tomato-spring-cloud
微服务聚合支付（第四方支付）系统，仅用于学习支付业务逻辑。

## 项目架构

```lua
tomato-spring-cloud
├── tomato-module-remit -- 打款中心
├── tomato-gateway -- 基于Spring Cloud Gateway的微服务API网关服务
├── tomato-module-monitor -- 基于Spring Boot Admin的微服务监控中心[8101]
└── config -- 配置中心存储的配置
```
