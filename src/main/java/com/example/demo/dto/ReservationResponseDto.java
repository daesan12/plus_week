package com.example.demo.dto;

import com.example.demo.entity.Reservation;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReservationResponseDto {
    private Long id;
    private String nickname;
    private String itemName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public ReservationResponseDto(Long id, String nickname, String itemName, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.nickname = nickname;
        this.itemName = itemName;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    // Reservation 객체를 받아서 DTO로 변환
    public ReservationResponseDto(Reservation updatedReservation) {
        this.id = updatedReservation.getId();
        this.nickname = updatedReservation.getUser().getNickname();
        this.itemName = updatedReservation.getItem().getName();
        this.startAt = updatedReservation.getStartAt();
        this.endAt = updatedReservation.getEndAt();
    }
}
