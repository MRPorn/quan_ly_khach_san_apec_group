package com.example.social_login.model.roomType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RoomTypeModel {

    private Integer id;

    private String code;

    private String description;

    private Integer size;

    private Integer numOfBed;

    private Integer maxAdult;

    private Integer maxChild;
}
