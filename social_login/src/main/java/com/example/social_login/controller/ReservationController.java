package com.example.social_login.controller;

import com.example.social_login.model.ReservationModel;
import com.example.social_login.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel-management/reservation")
@RequiredArgsConstructor

public class ReservationController {

    private final ReservationService service;

//    @Secured({"ADMIN", "USER"})
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/reservation-page")
    public ResponseEntity<List<ReservationModel>> getAllReservation(
            @RequestParam(defaultValue = "0", name = "page") int page
    ) {
        return ResponseEntity.ok(service.getAllReservation(page));
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ADMIN")
    @PostMapping("/add-reservation")
    public ResponseEntity<ReservationModel> saveRoomType(
            @RequestBody ReservationModel reservationModel
    ) {
        System.out.println(reservationModel.toString());
        return ResponseEntity.ok(service.saveReservation(reservationModel));
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ADMIN")
    @PutMapping("/update-reservation")
    public ResponseEntity<ReservationModel> updateStatus(
            @RequestBody ReservationModel reservationModel
    ) {
        return ResponseEntity.ok(service.saveReservation(reservationModel));
    }
}
