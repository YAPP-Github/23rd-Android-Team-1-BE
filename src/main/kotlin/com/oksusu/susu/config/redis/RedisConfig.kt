package com.oksusu.susu.config.redis

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory

@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig(
    private val properties: RedisProperties,
) {
    /**
     * Redis Config
     * - non-cluster-mode
     */
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(properties.host, properties.port)
    }
}