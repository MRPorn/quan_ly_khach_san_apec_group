package com.example.social_login.service;

import com.example.social_login.entity.RoomEntity;
import com.example.social_login.model.RoomModel;

import java.util.List;

public interface RoomService {

    List<RoomModel> getAllRoom(int page);

    RoomModel saveRoom (RoomModel roomModel);

//    RoomModel updateRoom(RoomModel roomModel);

    RoomModel convertRoomEntityToRoomModel (RoomEntity roomEntity);

    RoomEntity convertRoomModelToRoomEntity (RoomModel roomModel);
}
