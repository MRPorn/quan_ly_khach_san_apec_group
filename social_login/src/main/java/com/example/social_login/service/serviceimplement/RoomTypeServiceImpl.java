package com.example.social_login.service.serviceimplement;

import com.example.social_login.entity.RoomTypeEntity;
import com.example.social_login.model.RoomTypeModel;
import com.example.social_login.repository.RoomTypeRepository;
import com.example.social_login.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomTypeModel> getAllRoomType(int page) {

        Pageable pageable = PageRequest.of(page, 2);

        Page<RoomTypeEntity> roomTypePage = roomTypeRepository.findAll(pageable);

        return roomTypePage.getContent()
                .stream()
                .map(this::convertRoomTypeEntityToRoomTypeModel)
                .collect(Collectors.toList());
    }

    @Override
    public RoomTypeModel saveRoomType(RoomTypeModel roomTypeModel) {

        RoomTypeEntity roomTypeEntity = convertRoomTypeModelToRoomTypeEntity(roomTypeModel);

        if (roomTypeModel.getId() != null){
            RoomTypeEntity roomTypeEntityResultUpdate = roomTypeRepository.updateRoomTypeEntity(
                    roomTypeEntity.getDescription(),
                    roomTypeEntity.getSize(),
                    roomTypeEntity.getNumOfBed(),
                    roomTypeEntity.getMaxAdult(),
                    roomTypeEntity.getMaxChild(),
                    roomTypeEntity.getId()
            );

            return convertRoomTypeEntityToRoomTypeModel(roomTypeEntityResultUpdate);

        }

        RoomTypeEntity roomTypeEntityResultSave = roomTypeRepository.saveRoomTypeEntity(
                roomTypeEntity.getCode(),
                roomTypeEntity.getDescription(),
                roomTypeEntity.getSize(),
                roomTypeEntity.getNumOfBed(),
                roomTypeEntity.getMaxAdult(),
                roomTypeEntity.getMaxChild()
        );

        return convertRoomTypeEntityToRoomTypeModel(roomTypeEntityResultSave);

    }

//    @Override
//    public RoomTypeModel updateRoomType(RoomTypeModel roomTypeModel) {
//
////        RoomTypeEntity roomTypeEntity = roomTypeRepository.findRoomTypeById(roomTypeModel.getId());
////
////        roomTypeEntity.setCode(roomTypeModel.getCode());
////        roomTypeEntity.setDescription(roomTypeModel.getDescription());
////        roomTypeEntity.setSize(roomTypeModel.getSize());
////        roomTypeEntity.setNumOfBed(roomTypeModel.getNumOfBed());
////        roomTypeEntity.setMaxAdult(roomTypeModel.getMaxAdult());
////        roomTypeEntity.setMaxChild(roomTypeModel.getMaxChild());
//
////        RoomTypeEntity roomTypeEntity = roomTypeRepository.updateRoomTypeEntity(
////                roomTypeModel.getDescription(),
////                roomTypeModel.getSize(),
////                roomTypeModel.getNumOfBed(),
////                roomTypeModel.getMaxAdult(),
////                roomTypeModel.getMaxChild(),
////                roomTypeModel.getId()
////        );
////
////
////        return convertRoomTypeEntityToRoomTypeModel(roomTypeEntity);
//return null;
//    }

    @Override
    public RoomTypeModel convertRoomTypeEntityToRoomTypeModel(RoomTypeEntity roomTypeEntity) {

        return RoomTypeModel.builder()
                .id(roomTypeEntity.getId())
                .code(roomTypeEntity.getCode())
                .description(roomTypeEntity.getDescription())
                .size(roomTypeEntity.getSize())
                .maxAdult(roomTypeEntity.getMaxAdult())
                .maxChild(roomTypeEntity.getMaxChild())
                .numOfBed(roomTypeEntity.getNumOfBed())
                .build();
    }

    @Override
    public RoomTypeEntity convertRoomTypeModelToRoomTypeEntity(RoomTypeModel roomTypeModel) {

        return RoomTypeEntity.builder()
                .id(roomTypeModel.getId())
                .code(roomTypeModel.getCode())
                .description(roomTypeModel.getDescription())
                .size(roomTypeModel.getSize())
                .maxAdult(roomTypeModel.getMaxAdult())
                .maxChild(roomTypeModel.getMaxChild())
                .numOfBed(roomTypeModel.getNumOfBed())
                .build();
    }
}
