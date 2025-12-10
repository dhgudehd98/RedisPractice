package com.sh.redisproject.common.Redis.data.ExchangeReservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisExchangeReservationService {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String primaryKey = "exchange:reservation";

    /**
     * 환전 예약 저장
     * @param reservationId 예약 ID
     * @param reservationObj ID에 해당하는 Row
     */
    public void saveReservation(String reservationId, Object reservationObj) {
        redisTemplate.opsForHash().put(primaryKey, reservationId, reservationObj);
    }

}