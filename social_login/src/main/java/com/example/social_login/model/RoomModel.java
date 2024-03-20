package com.example.social_login.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomModel {

    private Integer id;

    private String code;

    private String description;

    private String name;

    private String floor;

    private Integer roomTypeId;
}
