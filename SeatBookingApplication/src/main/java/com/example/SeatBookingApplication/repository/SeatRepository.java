package com.example.SeatBookingApplication.repository;
//package com.example.seatbooking.repository;

import com.example.SeatBookingApplication.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    Optional<Seat> findBySeatNumber(String seatNumber);
}

