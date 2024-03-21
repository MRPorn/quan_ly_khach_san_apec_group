package com.example.social_login.service;

import com.example.social_login.entity.ReservationEntity;
import com.example.social_login.model.ReservationModel;

import java.util.List;

public interface ReservationService {

    List<ReservationModel> getAllReservation(int page);

    ReservationModel saveReservation(ReservationModel reservationModel);

//    ReservationModel updateStatus(ReservationModel reservationModel);

    ReservationModel convertReservationEntityToReservationModel(ReservationEntity reservationEntity);

    ReservationEntity convertReservationModelToReservationEntity(ReservationModel reservationModel);
}
