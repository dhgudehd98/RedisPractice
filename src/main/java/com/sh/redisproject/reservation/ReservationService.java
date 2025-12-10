package com.sh.redisproject.reservation;

import com.sh.redisproject.Member.repository.MemberRepository;
import com.sh.redisproject.RedisProjectApplication;
import com.sh.redisproject.common.Redis.data.ExchangeReservation.RedisExchangeReservationService;
import com.sh.redisproject.common.dto.ExchangeReservationDto;
import com.sh.redisproject.common.entity.ExchangeReservation;
import com.sh.redisproject.common.entity.Member;
import com.sh.redisproject.common.enums.ResStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final RedisExchangeReservationService redisExchangeReservationService;
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;


    /**
     * 일단 여기는 일괄등록을 한번 Test 해보기 위해 설정한 코드
     */
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

    /**
     * 환전 예약 신청 시 Redis Update
     */
    @Transactional
    public void insertToRedis() {
        Long memberId = 6L;
        Member member = memberRepository.findById(memberId).get();


        ExchangeReservation reservation = new ExchangeReservation(
                member,
                new BigDecimal("944.74"),  // exchange rate
                "EUR",                     // currencyIn
                50000L,                    // amountIn
                "JPY",                     // currencyOut
                20000L,                    // amountOut
                LocalDateTime.of(2025, 12, 6, 10, 58, 40), // res_time
                ResStatus.SUCCESS,         // resStatus
                'N',                       // alarmStatus
                null                       // failReason
        );

        ExchangeReservationDto dto = ExchangeReservationDto.fromEntity(reservationRepository.save(reservation));
        redisExchangeReservationService.saveReservation(String.valueOf(dto.getId()), dto);

    }

    /**
     * 환전 예약 성공 시 RedisUpdate
     */

    @Transactional
    public void deleteReservationToRedis() {

    }
}