package com.example.social_login.repository;

import com.example.social_login.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Query(value = """
            select id, code, name, description, floor, room_type_id
            from "room"
            where id = :id;""", nativeQuery = true)
    RoomEntity findRoomById(int id);

    @Query(value = """
            insert into room (code, name, description, floor, room_type_id)
            values (:code, :name, :description, :floor, :roomTypeId)
            returning id, code, name, description, floor, room_type_id""", nativeQuery = true)
    RoomEntity saveRoomEntity(String code,
                              String name,
                              String description,
                              String floor,
                              Integer roomTypeId
                              );

    @Query(value = """
            update room
            set name = :name, description = :description, floor = :floor, room_type_id = :roomTypeId
            where id = :id
            returning id, code, name, description, floor, room_type_id;""", nativeQuery = true)
    RoomEntity updateRoomEntity(String name,
                                String description,
                                String floor,
                                Integer roomTypeId,
                                Integer id
                                );
}
