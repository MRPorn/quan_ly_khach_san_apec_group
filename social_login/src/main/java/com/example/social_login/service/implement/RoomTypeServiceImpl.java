package com.example.social_login.service.implement;

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

        return  roomTypePage.getContent()
                            .stream()
                            .map(this::convertRoomTypeEntityToRoomTypeModel)
                            .collect(Collectors.toList());
    }

    @Override
    public RoomTypeModel saveRoomType(RoomTypeModel roomTypeModel) {

        RoomTypeEntity roomTypeEntity = roomTypeRepository.save(
                convertRoomTypeModelToRoomTypeEntity(roomTypeModel)
        );

        return convertRoomTypeEntityToRoomTypeModel(roomTypeEntity);

    }

    @Override
    public RoomTypeModel updateRoomType(int id, RoomTypeModel roomTypeModel) {

        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(id).orElse(null);

        assert roomTypeEntity != null;

        roomTypeEntity.setCode(roomTypeModel.getCode());
        roomTypeEntity.setDescription(roomTypeModel.getDescription());
        roomTypeEntity.setSize(roomTypeModel.getSize());
        roomTypeEntity.setNumOfBed(roomTypeModel.getNumOfBed());
        roomTypeEntity.setMaxAdult(roomTypeModel.getMaxAdult());
        roomTypeEntity.setMaxChild(roomTypeModel.getMaxChild());

        return saveRoomType(convertRoomTypeEntityToRoomTypeModel(roomTypeEntity));
    }

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

        return    RoomTypeEntity.builder()
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