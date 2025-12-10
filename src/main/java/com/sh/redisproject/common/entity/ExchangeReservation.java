package com.sh.redisproject.common.entity;

import com.sh.redisproject.common.enums.ResStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "EXCHANGE_RESERVATION")
public class ExchangeReservation {

    @Id @GeneratedValue
    @Column(name = "res_id")
    private Long id;

    // 회원과 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal exchangeRate; // 희망환율

    private String currencyIn;
    private Long amountIn;

    private Long amountOut;
    private String currencyOut;


    private LocalDateTime resTime; // 환전 시간

    @Enumerated(EnumType.STRING)
    private ResStatus resStatus; // 환전상태

    private char alarmStatus; // 알람 상태

    private String failReason;

    @PrePersist
    protected void onCreate() {
        this.resTime = LocalDateTime.now();
    }

    public ExchangeReservation(
            Member member,
            BigDecimal reservationRate,
            String currencyIn,
            Long amountIn,
            String currencyOut,
            Long amountOut,
            LocalDateTime localDateTime, ResStatus success, char n, Object o) {
        this.member = member;
        this.exchangeRate = reservationRate;

        this.currencyIn = currencyIn;
        this.amountIn = amountIn;

        this.currencyOut = currencyOut;
        this.amountOut = amountOut;

        this.resTime = LocalDateTime.now();
        this.resStatus = ResStatus.WAIT;
        this.alarmStatus = 'N';
    }

    public ExchangeReservation() {
    }

    @Override
    public String toString() {
        return "ExchangeReservation{" +
                "id=" + id +
                ", member=" + member +
                ", exchangeRate=" + exchangeRate +
                ", currencyIn='" + currencyIn + '\'' +
                ", amountIn=" + amountIn +
                ", amountOut=" + amountOut +
                ", currencyOut='" + currencyOut + '\'' +
                ", resTime=" + resTime +
                ", resStatus=" + resStatus +
                ", alarmStatus=" + alarmStatus +
                '}';
    }
}

