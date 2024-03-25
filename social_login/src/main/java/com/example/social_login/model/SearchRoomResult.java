package com.example.social_login.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchRoomResult {

    private Integer roomId;

    private String roomName;

    private String description;

    private Integer reservationRoomCount;

    private Double averagePrice;

}
