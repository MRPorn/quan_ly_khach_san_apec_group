package com.example.social_login.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ROOM_TYPE")
public class RoomTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String description;

    private Integer size;

    private Integer numOfBed;

    private Integer maxAdult;

    private Integer maxChild;
}
