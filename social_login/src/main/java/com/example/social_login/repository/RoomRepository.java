package com.example.social_login.repository;

import com.example.social_login.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

//    @Query(value = "", nativeQuery = true)
//    RoomEntity findRoomById();
//
//    @Query(value = "", nativeQuery = true)
//    RoomEntity saveRoomEntity();
//
//    @Query(value = "", nativeQuery = true)
//    RoomEntity updateRoomEntity();
}
