# 使用发令枪并发调用http地址
    countdownLatch 写了一个发令枪的实例
# SpringBoot集成resttemplate
 写了SpringBoot集成resttemplate的使用方法
  restTemplate
# 集成异步调用方法
    async
    两个方法，一个不对返回做处理，另一个对返回值做处理
# 集成SpringTask 定时任务
      task
      参数说明
        @Scheduled所支持的参数： 
        1. cron：cron表达式，指定任务在特定时间执行； 
        2. fixedDelay：表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms； 
        3. fixedDelayString：与fixedDelay含义一样，只是参数类型变为String； 
        4. fixedRate：表示按一定的频率执行任务，参数类型为long，单位ms； 
        5. fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String； 
        6. initialDelay：表示延迟多久再第一次执行任务，参数类型为long，单位ms； 
        7. initialDelayString：与initialDelay的含义一样，只是将参数类型变为String； 
        8. zone：时区，默认为当前时区，一般没有用到。
        
# 集成了自定义注解的使用
    customTags
## 1 自定义注解--拦截器
    interceptor
    这里实现了一个权限的控制
        访问地址 http://localhost:8080/admin?role=111
        controller 通过@Access(authorities = {"admin"})注解拦截role=111后面是否有权限
        
## 2 自定义注解--切面
    async
## 3 自定义注解--spring mvc（未实现）
    customservice 
    
# 4 集成分布式事物实现方法
    distributed
# 5 增加了读取配置文件配置属性的方法
    readApplication
    写了两种程序读取配置文件的方法
    读取配置文件中的list配置
    
# 设计模式
    1 Observe 观察者模式
         被观察者中集成了观察者的方法，当调用被观察者是，激发了观察者里的方法
         耦合性过高
    2 template 魔板模式
    
# redis自增id用法
    redisOrderNumber