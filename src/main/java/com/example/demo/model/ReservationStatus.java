package com.example.demo.model;

public enum ReservationStatus {
    PENDING, APPROVED, CANCELED, EXPIRED;

    public boolean canTransitionTo(ReservationStatus status) {
        switch (this) {
            case PENDING:
                return status == APPROVED || status == CANCELED || status == EXPIRED;
            case APPROVED:
                return status == CANCELED;
            case EXPIRED:
                return false;
           default:
                return false;
        }
    }

    // 문자열을 Enum으로 변환
    public static ReservationStatus fromString(String value) {
        try {
            return ReservationStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("없는 상태입니다 " + value);
        }
    }

}
