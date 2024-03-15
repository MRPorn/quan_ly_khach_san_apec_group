package com.example.social_login.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "RESERVATION")
public class Reservation {

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

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

}
