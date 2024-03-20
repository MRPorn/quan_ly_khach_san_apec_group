package com.example.social_login.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
public class ReservationModel {

    private Integer id;

    private String code;

    private String guestName;

    private String guestIdNo;

    private String guestPhone;

    private String guestEmail;

    private LocalDate createDate;

    private Long price;

    private Integer status;

    private Integer roomTypeId;

    private Integer roomId;

}
