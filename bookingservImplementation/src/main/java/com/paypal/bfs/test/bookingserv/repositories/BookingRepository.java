package com.paypal.bfs.test.bookingserv.repositories;

import com.paypal.bfs.test.bookingserv.datamodel.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findById(Integer bookingId);
}
