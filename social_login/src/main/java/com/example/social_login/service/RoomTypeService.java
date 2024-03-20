package com.example.social_login.service;

import com.example.social_login.entity.RoomTypeEntity;
import com.example.social_login.model.RoomTypeModel;

import java.util.List;

public interface RoomTypeService {

    List<RoomTypeModel> getAllRoomType(int page);

    RoomTypeModel saveRoomType(RoomTypeModel roomTypeModel);

    RoomTypeModel updateRoomType(int id, RoomTypeModel roomTypeModel);

    RoomTypeModel convertRoomTypeEntityToRoomTypeModel(RoomTypeEntity roomTypeEntity);

    RoomTypeEntity convertRoomTypeModelToRoomTypeEntity(RoomTypeModel roomTypeModel);
}
