package com.sh.redisproject;

import com.sh.redisproject.reservation.ReservationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedisProjectApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RedisProjectApplication.class, args);
        ReservationService reservationService = context.getBean(ReservationService.class);


//        reservationService.saveToRedis();
        reservationService.insertToRedis();
//        reservationService.deleteReservationToRedis();
    }

}
