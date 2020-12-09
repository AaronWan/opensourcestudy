一. 为什么研究该种部署方式

公司封装的client在主从切换时,没有进行实时的切换导致很多连接出现了异常: 当前slave不可以写数据

二. Sentinel机制的学习

https://www.cnblogs.com/duanxz/p/4701831.html

三. Sentinel环境部署

1. 下载redis原码 http://www.redis.cn/download.html  编译
2. 配置主从
    1. 主 ./src/redis-server redis.conf
    2. 从 ./src/redis-server redis_slave1.conf 将redis.conf文件进行调整后改为slave1同时在端口下面增加 slaveof 主的ip  主的端口
3. 配置哨兵
    1. ./src/redis-sentinel sentinel_self_1.conf
    2. ./src/redis-sentinel sentinel_self_2.conf
    3. 注意其中master
