package com.example.social_login.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "RESERVATION")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
