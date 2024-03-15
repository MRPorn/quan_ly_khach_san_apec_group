package com.example.social_login.services;

import com.example.social_login.model.Room;
import com.example.social_login.model.RoomType;
import com.example.social_login.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServies {

    private  final RoomRepository roomRepository;

    public List<Room> getAllRoom(int page){
        Pageable pageable = PageRequest.of(page,2);
        Page<Room> roomPage = roomRepository.findAll(pageable);
        return roomPage.getContent();
    }

    public Room saveRoom (Room room){
        System.out.println("o day");
        return roomRepository.save(room);

    }

    public Room updateRoom(int id, Room room) {

        Room room1 = roomRepository.findById(id).orElse(null);

        assert room1 != null;
        room1.setCode(room.getCode());
        room1.setName(room.getName());
        room1.setDescription(room.getDescription());
        room1.setFloor(room.getFloor());
        room1.setRoomType(room.getRoomType());

        return roomRepository.save(room1);
    }
}

