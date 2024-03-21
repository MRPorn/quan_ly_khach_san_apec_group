package com.example.social_login.controller;

import com.example.social_login.model.RoomModel;
import com.example.social_login.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-khach-san/phong")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

//    @Secured({"ADMIN" ,"USER"})
    @GetMapping("/danh-sach-phong")
    public ResponseEntity<List<RoomModel>> pageRoom(
            @RequestParam(defaultValue = "0", name = "page") int page
    ){

        return ResponseEntity.ok(service.getAllRoom(page));
    }

//    @Secured("ADMIN")
    @PostMapping("/them-phong-moi")
    public ResponseEntity<RoomModel> saveRoomType(
            @RequestBody RoomModel roomModel
    ){
        return ResponseEntity.ok(service.saveRoom(roomModel));
    }

//    @Secured("ADMIN")
    @PutMapping("/cap-nhat-phong")
    public ResponseEntity<RoomModel> update(
            @RequestBody RoomModel roomModel
    ){
        return ResponseEntity.ok(service.saveRoom(roomModel));
    }
}
