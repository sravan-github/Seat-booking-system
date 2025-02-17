package com.example.SeatBookingApplication.controller;
//package com.example.seatbooking.controller;

import com.example.SeatBookingApplication.model.Seat;
import com.example.SeatBookingApplication.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // Allow React frontend
@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public ResponseEntity<List<com.example.SeatBookingApplication.model.Seat>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    @PostMapping("/book/{seatNumber}")
    public ResponseEntity<?> bookSeat(@PathVariable String seatNumber) {
        try {
            Seat bookedSeat = seatService.bookSeat(seatNumber);
            return ResponseEntity.ok(bookedSeat);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/cancel/{seatNumber}")
    public ResponseEntity<?> cancelSeat(@PathVariable String seatNumber) {
        try {
            Seat canceledSeat = seatService.cancelBooking(seatNumber);
            return ResponseEntity.ok(canceledSeat);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
