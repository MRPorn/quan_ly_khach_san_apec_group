package com.example.social_login.repository;

import com.example.social_login.entity.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Integer> {

    @Query(value = "select * from \"room_type\" where id = :id", nativeQuery = true)
    RoomTypeEntity findRoomTypeById(int id);

    @Query(value = """
            insert into "room_type" (code, description, size, num_of_bed, max_adult, max_child)
            values (?1, ?2, ?3, ?4, ?5, ?6)
            returning id, code, description, size, num_of_bed, max_adult, max_child;""", nativeQuery = true)
    RoomTypeEntity saveRoomTypeEntity(String code,
                                      String description,
                                      Integer size,
                                      Integer num_of_bed,
                                      Integer max_adult,
                                      Integer max_child
                                      );

    @Query(value = """
            update "room_type"
            set\s
                description = :description,\s
                size = :size,\s
                num_of_bed = :num_of_bed,\s
                max_adult = :max_adult,\s
                max_child = :max_child
            where id = :id
            returning id, code, description, size, num_of_bed, max_adult, max_child;""", nativeQuery = true)
    RoomTypeEntity updateRoomTypeEntity(String description,
                                        Integer size,
                                        Integer num_of_bed,
                                        Integer max_adult,
                                        Integer max_child,
                                        Integer id
                                        );
}
