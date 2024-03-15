package com.example.social_login.controller;

import com.example.social_login.model.Reservation;
import com.example.social_login.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-khach-san/dat-phong")
@RequiredArgsConstructor

public class ReservationController {

    private final ReservationService service;

    @GetMapping("/danh-sach-dat-phong")
    public ResponseEntity<?> getAllReservation(
            @RequestParam(defaultValue = "0", name = "page") int page
    ){
        List<Reservation> list = service.getAllReservation(page);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/them-moi")
    public ResponseEntity<?> saveRoomType(@RequestBody Reservation reservation){
        return ResponseEntity.ok(service.saveReservation(reservation));
    }

    @PutMapping("/cap-nhat-trang-thai/{id}")
    public ResponseEntity<?> updateStatus(
            @PathVariable int id,
            @RequestBody Reservation reservation
    ){
        return ResponseEntity.ok(service.updateStatus(id, reservation));
    }
}
