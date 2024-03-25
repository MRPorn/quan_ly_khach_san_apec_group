package com.example.social_login.service.serviceimplement;

import com.example.social_login.entity.RoomEntity;
import com.example.social_login.model.RoomModel;
import com.example.social_login.model.SearchRoomResult;
import com.example.social_login.repository.RoomRepository;
import com.example.social_login.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private  final RoomRepository roomRepository;

    @Override
    public List<RoomModel> getAllRoom(int page){

        Pageable pageable = PageRequest.of(page,2);

        Page<RoomEntity> roomPage = roomRepository.findAll(pageable);

        return  roomPage.getContent()
                        .stream()
                        .map(this::convertRoomEntityToRoomModel)
                        .collect(Collectors.toList());
    }

    @Override
    public RoomModel saveRoom (RoomModel roomModel){

        RoomEntity roomEntity = convertRoomModelToRoomEntity(roomModel);

        if (roomModel.getId() != null){
            RoomEntity roomEntityResultUpdate = roomRepository.updateRoomEntity(
                    roomEntity.getName(),
                    roomEntity.getDescription(),
                    roomEntity.getFloor(),
                    roomEntity.getRoomTypeId(),
                    roomEntity.getId()
            );

            return convertRoomEntityToRoomModel(roomEntityResultUpdate);
        }

        RoomEntity roomEntityResult = roomRepository.saveRoomEntity(
                roomEntity.getCode(),
                roomEntity.getName(),
                roomEntity.getDescription(),
                roomEntity.getFloor(),
                roomEntity.getRoomTypeId()
        );


        return convertRoomEntityToRoomModel(roomEntityResult);

    }

//    @Override
//    public RoomModel updateRoom(RoomModel roomModel) {
//
//        RoomEntity roomEntity = roomRepository.findById(id).orElse(null);
//
//        assert roomEntity != null;
//
//        roomEntity.setCode(roomModel.getCode());
//        roomEntity.setName(roomModel.getName());
//        roomEntity.setDescription(roomModel.getDescription());
//        roomEntity.setFloor(roomModel.getFloor());
//        roomEntity.setRoomTypeId(roomModel.getRoomTypeId());
//
//        return saveRoom(convertRoomEntityToRoomModel(roomEntity));
//    }

    @Override
    public RoomModel convertRoomEntityToRoomModel (RoomEntity roomEntity){

        return RoomModel.builder()
                        .id(roomEntity.getId())
                        .code(roomEntity.getCode())
                        .name(roomEntity.getName())
                        .description(roomEntity.getDescription())
                        .floor(roomEntity.getFloor())
                        .roomTypeId(roomEntity.getRoomTypeId())
                        .build();
    }

    @Override
    public RoomEntity convertRoomModelToRoomEntity (RoomModel roomModel){

        return RoomEntity.builder()
                        .id(roomModel.getId())
                        .code(roomModel.getCode())
                        .name(roomModel.getName())
                        .description(roomModel.getDescription())
                        .floor(roomModel.getFloor())
                        .roomTypeId(roomModel.getRoomTypeId())
                        .build();
    }

    @Override
    public List<SearchRoomResult> searchRoomByAveragePrice(int averagePrice) {

        return roomRepository.searchRoomByAveragePrice(averagePrice)
                .stream()
                .map(x -> {
                    SearchRoomResult searchRoomResult = new SearchRoomResult();
                    searchRoomResult.setRoomId((Integer) x[0]);
                    searchRoomResult.setRoomName((String) x[1]);
                    searchRoomResult.setDescription((String) x[2]);
                    searchRoomResult.setReservationRoomCount(((Number) x[3]).intValue());
                    searchRoomResult.setAveragePrice(((Number) x[4]).doubleValue());
                    return searchRoomResult;
                })
                .collect(Collectors.toList());
    }
}

