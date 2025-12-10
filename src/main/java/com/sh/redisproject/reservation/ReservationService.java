package com.sh.redisproject.reservation;

import com.sh.redisproject.RedisProjectApplication;
import com.sh.redisproject.common.Redis.data.ExchangeReservation.RedisExchangeReservationService;
import com.sh.redisproject.common.dto.ExchangeReservationDto;
import com.sh.redisproject.common.entity.ExchangeReservation;
import com.sh.redisproject.common.enums.ResStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final RedisExchangeReservationService redisExchangeReservationService;
    private final ReservationRepository reservationRepository;


    @Transactional
    public void saveToRedis() {
        List<ExchangeReservation> list =  reservationRepository.findByResStatus(ResStatus.WAIT);

        for (ExchangeReservation reservation : list) {
            ExchangeReservationDto dto = ExchangeReservationDto.fromEntity(reservation);

            log.info("Entity Reservation Change To Entity ");
            log.info(dto.toString());

            String id = String.valueOf(dto.getMemberId());
            redisExchangeReservationService.saveReservation(id, dto);
        }
    }
}