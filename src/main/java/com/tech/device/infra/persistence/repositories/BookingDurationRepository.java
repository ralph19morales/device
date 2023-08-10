package com.tech.device.infra.persistence.repositories;

import com.tech.device.infra.persistence.entities.BookingDurationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingDurationRepository extends JpaRepository<BookingDurationEntity, Long> {

    Optional<BookingDurationEntity> findTopByBookingIdOrderByIdDesc(Long bookingId);
}
