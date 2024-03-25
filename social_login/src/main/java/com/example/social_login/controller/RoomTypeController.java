package com.example.social_login.controller;

import com.example.social_login.model.RoomTypeModel;
import com.example.social_login.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel-management/room-type")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService service;

//    @Secured({"ADMIN" ,"USER"})
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/room-type-page")
    public ResponseEntity<List<RoomTypeModel>> pageRoomType(
            @RequestParam(defaultValue = "0", name = "page") int page
    ){

        return ResponseEntity.ok(service.getAllRoomType(page));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add-room-type")
    public ResponseEntity<RoomTypeModel> saveRoomType(
            @RequestBody RoomTypeModel roomTypeModel){
        return ResponseEntity.ok(service.saveRoomType(roomTypeModel));
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update-room-type")
    public ResponseEntity<RoomTypeModel> updateRoom(
            @RequestBody RoomTypeModel roomTypeModel
    ){
        return ResponseEntity.ok(service.saveRoomType(roomTypeModel));
    }


}
