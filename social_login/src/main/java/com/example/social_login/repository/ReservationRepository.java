package com.example.social_login.repository;

import com.example.social_login.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {

    @Query(value = """
            select
                id, code, guest_name, guest_id_no, guest_phone, guest_email,\s
                create_date, price, status, room_type_id, room_id
            from reservation
            where id = :id""", nativeQuery = true)
    ReservationEntity findReservationEntityById(int id);

    @Query(value = """
            insert into reservation (code, guest_name, guest_id_no, guest_phone, guest_email,\s
                create_date, price, status, room_type_id, room_id)
            values (:code, :guestName, :guestIdNo, :guestPhone,\s
                :guestEmail, :createDate, :price, :status, :roomTypeId, :roomId)
            RETURNING id, code, guest_name, guest_id_no, guest_phone, guest_email,\s
                create_date, price, status, room_type_id, room_id;""",nativeQuery = true)
    ReservationEntity saveReservationEntity(String code,
                                     String guestName,
                                     String guestIdNo,
                                     String guestPhone,
                                     String guestEmail,
                                     LocalDate createDate,
                                     Long price,
                                     Integer status,
                                     Integer roomTypeId,
                                     Integer roomId
                                     );

    @Query(value = """
            update reservation
            set guest_name = :guestName, guest_id_no = :guestIdNo, guest_phone = :guestPhone, guest_email = :guestEmail,
                create_date = :createDate, price = :price, status = :status, room_type_id = :roomTypeId, room_id = :roomId
            where id = :id
            returning id, code, guest_name, guest_id_no, guest_phone, guest_email,\s
                create_date, price, status, room_type_id, room_id;""", nativeQuery = true)
    ReservationEntity updateStatusReservationEntity(String guestName,
                                                    String guestIdNo,
                                                    String guestPhone,
                                                    String guestEmail,
                                                    LocalDate createDate,
                                                    Long price,
                                                    Integer status,
                                                    Integer roomTypeId,
                                                    Integer roomId,
                                                    Integer id
                                                    );


}
