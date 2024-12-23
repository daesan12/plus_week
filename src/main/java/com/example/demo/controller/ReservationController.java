package com.example.demo.controller;

import com.example.demo.dto.ReservationRequestDto;
import com.example.demo.dto.ReservationResponseDto;
import com.example.demo.entity.Reservation;
import com.example.demo.model.ReservationStatus;
import com.example.demo.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public void createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        reservationService.createReservation(reservationRequestDto.getItemId(),
                                            reservationRequestDto.getUserId(),
                                            reservationRequestDto.getStartAt(),
                                            reservationRequestDto.getEndAt());
    }

    @PatchMapping("/{id}/update-status")
    public ResponseEntity<ReservationResponseDto> updateReservation(@PathVariable Long id, @RequestParam ReservationStatus status) {
        Reservation updatedReservation = reservationService.updateReservationStatus(id, status);
        return ResponseEntity.ok(new ReservationResponseDto(updatedReservation));
    }

    @GetMapping
    public List<ReservationResponseDto> findAll() {
        return  reservationService.getReservations();
    }

    @GetMapping("/search")
    public List<ReservationResponseDto> searchAll(@RequestParam(required = false) Long userId,
                          @RequestParam(required = false) Long itemId) {
       return reservationService.searchAndConvertReservations(userId, itemId);
    }
}
