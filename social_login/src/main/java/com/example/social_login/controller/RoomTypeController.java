package com.example.social_login.controller;

import com.example.social_login.model.Room;
import com.example.social_login.model.RoomType;
import com.example.social_login.services.RoomServies;
import com.example.social_login.services.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-khach-san/loai-phong")
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomTypeService servies;

    @GetMapping("/danh-sach-loai-phong")
    public ResponseEntity<List<RoomType>> pageRoomType(
            @RequestParam(defaultValue = "0", name = "page") int page
    ){

        return ResponseEntity.ok(servies.getAllRoomType(page));
    }

    @PostMapping("/them-loai-phong-moi")
    public ResponseEntity<?> saveRoomType(@RequestBody RoomType roomType){
        return ResponseEntity.ok(servies.saveRoomType(roomType));
    }

    @PutMapping("/cap-nhat-loai-phong/{id}")
    public ResponseEntity<?> updateRoom(
            @PathVariable int id,
            @RequestBody RoomType roomType
    ){
        return ResponseEntity.ok(servies.updateRoomType(id, roomType));
    }


}
