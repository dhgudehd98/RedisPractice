package com.sh.redisproject.common.Redis.configuration.standAlone;


import com.sh.redisproject.common.entity.ExchangeReservation;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class StandAloneConfig {

    @Value("${spring.data.redis.host}")
    private String host ;

    @Value("${spring.data.redis.port}")
    private int port ;
    @Bean
    public RedisConnectionFactory redisConnectFactory() {

        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);


        /**
         * Redis Key 값 설정
         * 구조
         * exchange:reservation(환전예약에 대한 데이터만 저장하는 부분)
         * - key : 1001(res_id) > value : 1001에 해당 하는 row
         * - key : 1002(res_id) > value : 1002에 해당 하는 row
         * - key : 1003(res_id) > value : 1003에 해당 하는 row
         */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        // Hash Key에 해당하는 Value 값은 JSON 형식으로 저장
        // 향후 Jackson2JsonRedisSerializer에 대한 부분은 없어질 예정이고, RedisSerialize().json()으로 바뀔 예정 그럼 build.gradle에 대한 부분도 수정필요
        Jackson2JsonRedisSerializer<ExchangeReservation> serializer =
                new Jackson2JsonRedisSerializer<>(ExchangeReservation.class);

        redisTemplate.setHashValueSerializer(serializer);

        return redisTemplate;
    }

}