package com.example.social_login.services;

import com.example.social_login.model.Room;
import com.example.social_login.model.RoomType;
import com.example.social_login.repository.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor

public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

    public List<RoomType> getAllRoomType(int page){
        Pageable pageable = PageRequest.of(page,2);
        Page<RoomType> roomTypePage = roomTypeRepository.findAll(pageable);
        return roomTypePage.getContent();
    }

    public String timKiemCode(RoomType roomType){

        return Objects.requireNonNull(roomTypeRepository.findById(roomType.getId()).orElse(null)).getCode();
    }

    public RoomType saveRoomType(RoomType roomType){

        return roomTypeRepository.save(roomType);

    }


    public RoomType updateRoomType(int id, RoomType roomType) {

        RoomType roomType1 = roomTypeRepository.findById(id).orElse(null);
        assert roomType1 != null;
        roomType1.setCode(roomType.getCode());
        roomType1.setDescription(roomType.getDescription());
        roomType1.setSize(roomType.getSize());
        roomType1.setNumOfBed(roomType.getNumOfBed());
        roomType1.setMaxAdult(roomType.getMaxAdult());
        roomType1.setMaxChild(roomType.getMaxChild());
        return roomTypeRepository.save(roomType1);
    }
}
