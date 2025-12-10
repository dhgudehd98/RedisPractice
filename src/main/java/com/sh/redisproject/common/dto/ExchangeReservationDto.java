package com.sh.redisproject.common.dto;

import com.sh.redisproject.common.enums.ResStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExchangeReservationDto {

    private Long id;
    private BigDecimal exchangeRate;
    private String currencyIn;
    private Long amountIn;
    private Long amountOut;
    private String currencyOut;
    private String resTime;
    private String resStatus;
    private char alarmStatus;
    private Long memberId;

    @Override
    public String toString() {
        return "ExchangeReservationDto{" +
                "id=" + id +
                ", exchangeRate=" + exchangeRate +
                ", currencyIn='" + currencyIn + '\'' +
                ", amountIn=" + amountIn +
                ", amountOut=" + amountOut +
                ", currencyOut='" + currencyOut + '\'' +
                ", resTime=" + resTime +
                ", resStatus=" + resStatus +
                ", alarmStatus=" + alarmStatus +
                ", memberId=" + memberId +
                '}';
    }

    // Entity → DTO 변환 메서드
    public static ExchangeReservationDto fromEntity(com.sh.redisproject.common.entity.ExchangeReservation reservation) {
        ExchangeReservationDto dto = new ExchangeReservationDto();
        dto.setId(reservation.getId());
        dto.setExchangeRate(reservation.getExchangeRate());
        dto.setCurrencyIn(reservation.getCurrencyIn());
        dto.setAmountIn(reservation.getAmountIn());
        dto.setAmountOut(reservation.getAmountOut());
        dto.setCurrencyOut(reservation.getCurrencyOut());
        dto.setResTime(reservation.getResTime() != null ? reservation.getResTime().toString() : null);
        dto.setResStatus(reservation.getResStatus() != null ? reservation.getResStatus().name() : null);
        dto.setAlarmStatus(reservation.getAlarmStatus());

        // Member 정보 일부 담기 (Lazy Proxy 문제 회피)
        if (reservation.getMember() != null) {
            dto.setMemberId(reservation.getMember().getId());
        }

        return dto;
    }
}