package com.example.social_login.services;

import com.example.social_login.model.Reservation;
import com.example.social_login.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<Reservation> getAllReservation(int page){

            Pageable pageable = PageRequest.of(page,2);

            Page<Reservation> reservationPage = reservationRepository.findAll(pageable);

            return reservationPage.getContent();

    }

    public Reservation saveReservation (Reservation reservation){

        return reservationRepository.save(reservation);

    }

    public Reservation updateStatus(int id, Reservation status) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);

        assert reservation != null;

        System.out.println(reservation.toString());

        reservation.setStatus(status.getStatus());

        return saveReservation(reservation);
    }
}
