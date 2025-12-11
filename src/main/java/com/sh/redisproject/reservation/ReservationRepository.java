package com.sh.redisproject.reservation;

import com.sh.redisproject.common.entity.ExchangeReservation;
import com.sh.redisproject.common.enums.ResStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ExchangeReservation, Long> {

    List<ExchangeReservation> findByResStatus(ResStatus status);

    Optional<ExchangeReservation> findById(Long resId);
}