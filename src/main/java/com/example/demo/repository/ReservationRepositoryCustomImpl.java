package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.demo.entity.QReservation.reservation;
import static com.example.demo.entity.QUser.user;
import static com.example.demo.entity.QItem.item;


public class ReservationRepositoryCustomImpl implements ReservationRepositoryCustom {
    ReservationRepositoryCustomImpl(EntityManager em) {
        queryFactory=new JPAQueryFactory(em);
    }
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Reservation> searchReservations(Long userId, Long itemId) {
        return queryFactory.selectFrom(reservation)
                .join(reservation.user, user).fetchJoin()
                .join(reservation.item, item).fetchJoin()
                .where(
                        userId != null ? reservation.user.id.eq(userId) : null,
                        itemId != null ? reservation.item.id.eq(itemId) : null
                )
                .fetch();
    }
}