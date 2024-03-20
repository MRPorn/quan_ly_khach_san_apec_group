package com.example.social_login.service;

import com.example.social_login.entity.ReservationEntity;
import com.example.social_login.model.reservation.ReservationModel;
import com.example.social_login.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public List<ReservationModel> getAllReservation(int page) {

        Pageable pageable = PageRequest.of(page, 2);

        Page<ReservationEntity> reservationPages = reservationRepository.findAll(pageable);

        return  reservationPages.getContent()
                                .stream()
                                .map(this::convertReservationEntityToReservationModel)
                                .collect(Collectors.toList());

    }

    public ReservationModel saveReservation(ReservationModel reservationModel) {

        ReservationEntity reservationEntity = reservationRepository.save(
                convertReservationModelToReservationEntity(reservationModel)
        );

        return convertReservationEntityToReservationModel(reservationEntity);

    }

    public ReservationModel updateStatus(int id, ReservationModel reservationModel) {

        ReservationEntity reservationEntity = reservationRepository.findById(id).orElse(null);

        assert reservationEntity != null;

        reservationEntity.setStatus(reservationModel.getStatus());

        return saveReservation(convertReservationEntityToReservationModel(reservationEntity));
    }

    public ReservationModel convertReservationEntityToReservationModel(ReservationEntity reservationEntity) {
        return  ReservationModel.builder()
                                .id(reservationEntity.getId())
                                .code(reservationEntity.getCode())
                                .guestName(reservationEntity.getGuestName())
                                .guestIdNo(reservationEntity.getGuestIdNo())
                                .guestEmail(reservationEntity.getGuestEmail())
                                .guestPhone(reservationEntity.getGuestPhone())
                                .createDate(reservationEntity.getCreateDate())
                                .price(reservationEntity.getPrice())
                                .status(reservationEntity.getStatus())
                                .roomTypeId(reservationEntity.getRoomTypeId())
                                .roomId(reservationEntity.getRoomId())
                                .build();
    }

    public ReservationEntity convertReservationModelToReservationEntity(ReservationModel reservationModel) {
        return ReservationEntity.builder()
                                .id(reservationModel.getId())
                                .code(reservationModel.getCode())
                                .guestName(reservationModel.getGuestName())
                                .guestIdNo(reservationModel.getGuestIdNo())
                                .guestEmail(reservationModel.getGuestEmail())
                                .guestPhone(reservationModel.getGuestPhone())
                                .createDate(reservationModel.getCreateDate())
                                .price(reservationModel.getPrice())
                                .status(reservationModel.getStatus())
                                .roomTypeId(reservationModel.getRoomTypeId())
                                .roomId(reservationModel.getRoomId())
                                .build();
    }
}
