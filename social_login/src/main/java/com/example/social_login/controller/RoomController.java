package com.example.social_login.controller;

import com.example.social_login.model.Room;
import com.example.social_login.model.RoomType;
import com.example.social_login.services.RoomServies;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quan-ly-khach-san/phong")
@RequiredArgsConstructor
public class RoomController {

    private final RoomServies servies;

    @GetMapping("/danh-sach-phong")
    public ResponseEntity<List<Room>> pageRoom(
            @RequestParam(defaultValue = "0", name = "page") int page
    ){

        return ResponseEntity.ok(servies.getAllRoom(page));
    }

    @PostMapping("/them-phong-moi")
    public ResponseEntity<?> saveRoomType(@RequestBody Room room){
        return ResponseEntity.ok(servies.saveRoom(room));
    }

    @PutMapping("/cap-nhat-phong/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody Room room
    ){
        return ResponseEntity.ok(servies.updateRoom(id, room));
    }
}
