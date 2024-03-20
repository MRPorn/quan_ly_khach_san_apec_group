package com.example.social_login.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String code;

    private String name;

    private String description;

    private String floor;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;
}
