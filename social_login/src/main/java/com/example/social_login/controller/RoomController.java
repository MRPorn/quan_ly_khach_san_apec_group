package com.example.social_login.controller;

import com.example.social_login.model.RoomModel;
import com.example.social_login.model.SearchRoomResult;
import com.example.social_login.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel-management/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    //    @Secured({"ADMIN" ,"USER"})
//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/room-page")
    public ResponseEntity<List<RoomModel>> pageRoom(
            @RequestParam(defaultValue = "0", name = "page") int page
    ) {

        return ResponseEntity.ok(service.getAllRoom(page));
    }

    @GetMapping("/search-room-by-average-price")
    public ResponseEntity<List<SearchRoomResult>> search(
            @RequestParam(defaultValue = "0", name = "average") int averagePrice
    ){
        return ResponseEntity.ok(service.searchRoomByAveragePrice(averagePrice));
    }

    //    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ADMIN")
    @PostMapping("/add-room")
    public ResponseEntity<RoomModel> saveRoomType(
            @RequestBody RoomModel roomModel
    ) {
        return ResponseEntity.ok(service.saveRoom(roomModel));
    }

    //    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ADMIN")
    @PutMapping("/update-room")
    public ResponseEntity<RoomModel> update(
            @RequestBody RoomModel roomModel
    ) {
        return ResponseEntity.ok(service.saveRoom(roomModel));
    }


}
