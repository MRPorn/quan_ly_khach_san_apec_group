package com.example.social_login.repository;

import com.example.social_login.entity.RoomEntity;
import com.example.social_login.model.SearchRoomResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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


    @Query(value = """
            select \s
                    room.id, room.name, room_type.description,\s
                    count(room_id) as "reservation room count" ,\s
                    avg(price) AS "average price"
            from\s
                    reservation
            join\s
                    room on reservation.room_id = room.id
            join\s
                    room_type on room.room_type_id = room_type.id
            group by\s
                    room_id, room.name, room_type.description, room.id
            having\s
                    room_id is not null and avg(price) > :averagePrice""",nativeQuery = true)
    List<Object[]> searchRoomByAveragePrice(Integer averagePrice);
}
