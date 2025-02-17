package com.example.SeatBookingApplication.service;
//package com.example.seatbooking.service;

import com.example.SeatBookingApplication.model.Seat;
import com.example.SeatBookingApplication.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    public Optional<Seat> getSeatByNumber(String seatNumber) {
        return seatRepository.findBySeatNumber(seatNumber);
    }

    public Seat bookSeat(String seatNumber) throws Exception {
        Seat seat = seatRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new Exception("Seat not found"));

        if (seat.isBooked()) {
            throw new Exception("Seat already booked!");
        }

        seat.setBooked(true);
        return seatRepository.save(seat);
    }

    public Seat cancelBooking(String seatNumber) throws Exception {
        Seat seat = seatRepository.findBySeatNumber(seatNumber)
                .orElseThrow(() -> new Exception("Seat not found"));

        if (!seat.isBooked()) {
            throw new Exception("Seat is not booked!");
        }

        seat.setBooked(false);
        return seatRepository.save(seat);
    }
}
