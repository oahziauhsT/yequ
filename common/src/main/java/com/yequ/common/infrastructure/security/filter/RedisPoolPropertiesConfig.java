package com.yequ.common.infrastructure.security.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis-config.pool")
@Data
public class RedisPoolPropertiesConfig {
    private String password;
    private String host;
    private int port;
    // 设置最大连接数，默认值为8.如果赋值为-1，则表示不限制；
    private int maxTotal;
    // 最大空闲连接数
    private int maxIdle;
    // 最小空闲连接数
    private int minIdle;
    //获取Jedis连接的最大等待时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException
    private Long maxWaitMillis;
    // 释放连接的扫描间隔（毫秒）,如果为负数,则不运行逐出线程, 默认-1
    private Long timeBetweenEvictionRunsMillis;
    // 连接最小空闲时间
    private Long minEvictableIdleTimeMillis;
    // 连接空闲多久后释放, 当空闲时间&gt;该值 且 空闲连接&gt;最大空闲连接数 时直接释放
    private Long softMinEvictableIdleTimeMillis;
    // 在获取Jedis连接时，自动检验连接是否可用
    private boolean testOnBorrow;
    // 在将连接放回池中前，自动检验连接是否有效
    private boolean testOnReturn;
    // 自动测试池中的空闲连接是否都是可用连接
    private boolean testWhileIdle;
    // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
    private boolean blockWhenExhausted;
    // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
    private int numTestsPerEvictionRun;
}
