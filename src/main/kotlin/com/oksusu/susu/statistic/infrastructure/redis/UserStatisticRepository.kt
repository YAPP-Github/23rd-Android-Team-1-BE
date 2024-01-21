package com.oksusu.susu.statistic.infrastructure.redis

import com.oksusu.susu.cache.Cache
import com.oksusu.susu.cache.CacheService
import com.oksusu.susu.cache.helper.CacheKeyGenerateHelper
import com.oksusu.susu.statistic.domain.UserStatistic
import org.springframework.stereotype.Repository

@Repository
class UserStatisticRepository(
    private val cacheService: CacheService,
    private val cacheKeyGenerateHelper: CacheKeyGenerateHelper,
) {
    suspend fun save(uid: Long, value: UserStatistic) {
        val key = cacheKeyGenerateHelper.getUserStatisticKey(uid)

        cacheService.set(Cache.getUserStatisticCache(key), value)
    }

    suspend fun getStatistic(uid: Long): UserStatistic? {
        val key = cacheKeyGenerateHelper.getUserStatisticKey(uid)

        return cacheService.getOrNull(Cache.getUserStatisticCache(key))
    }
}
