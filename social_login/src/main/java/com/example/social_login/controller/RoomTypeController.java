package com.example.social_login.controller;

import com.example.social_login.model.RoomTypeModel;
import com.example.social_login.service.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-khach-san/loai-phong")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService service;

//    @Secured({"ADMIN" ,"USER"})
    @GetMapping("/danh-sach-loai-phong")
    public ResponseEntity<List<RoomTypeModel>> pageRoomType(
            @RequestParam(defaultValue = "0", name = "page") int page
    ){

        return ResponseEntity.ok(service.getAllRoomType(page));
    }

//    @Secured("ADMIN")
    @PostMapping("/them-loai-phong-moi")
    public ResponseEntity<RoomTypeModel> saveRoomType(@RequestBody RoomTypeModel roomTypeModel){
        return ResponseEntity.ok(service.saveRoomType(roomTypeModel));
    }

//    @Secured("ADMIN")
    @PutMapping("/cap-nhat-loai-phong")
    public ResponseEntity<RoomTypeModel> updateRoom(
            @RequestBody RoomTypeModel roomTypeModel
    ){
        return ResponseEntity.ok(service.updateRoomType(roomTypeModel.getId(), roomTypeModel));
    }


}
