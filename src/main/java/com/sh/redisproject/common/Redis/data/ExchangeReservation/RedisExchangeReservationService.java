package com.sh.redisproject.common.Redis.data.ExchangeReservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisExchangeReservationService {

    private final StringRedisTemplate redisTemplate;
    private static final String primaryKey = "exchange:reservation";


    /**
     * 환전예약 신청시 Redis에 저장
     *
     * @param reservationId
     */
    public void saveReservation(Long reservationId) {
        redisTemplate.opsForSet().add(primaryKey, reservationId.toString());
    }

    /**
     * 환전예약 > 환전 신청 > 성공 || 실패 시, Redis에서 제거하기
     *
     * @param reservationId
     */
    public void deleteReservation(String reservationId) {
        redisTemplate.opsForSet().remove(primaryKey, reservationId);
    }

    /**
     * Redis에 저장된 모든 res_id에 대한값 추출
     *
     * @return
     */
    public Set<String> getListReservation() {
        log.info("RedisExchangeReservationService Init");
        return redisTemplate.opsForSet().members(primaryKey);
    }



}