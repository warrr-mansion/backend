package com.warrr.zipflex.api.house.dao;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HouseViewHistoryProcessor {

    private final RedisTemplate<String, String> redisTemplate;

    private static final int MAX_RECENT_VIEWS = 20;
    private static final String KEY_PREFIX = "house:recent-view:";
    
    @Async
    public void pushViewedHouseAsync(String memberUuid, Long houseInfoId) {
        String key = KEY_PREFIX + memberUuid;
        
        pushRecentView(key, houseInfoId);
        trimRecentViewList(key);
    }

    @Transactional
    private void pushRecentView(String key, Long houseInfoId) {
        redisTemplate.opsForList().leftPush(key, houseInfoId.toString());
    }

    @Transactional
    private void trimRecentViewList(String key) {
        redisTemplate.opsForList().trim(key, 0, MAX_RECENT_VIEWS - 1);
    }
    
}
