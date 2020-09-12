package com.game.presentation.controller.web;

import com.game.data.dto.ImageDto;
import com.game.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "apiOfUser")
@RequestMapping("/api/image")
public class ImageController
{
    @Autowired
    private IImageService iImageService;
    // page and limit not null
    @GetMapping("/{gameId}")
    public ResponseEntity<List<ImageDto>> getImageActive(@PathVariable Integer gameId,
                                                         @RequestParam(value = "activePlay" , required = false) Boolean activePlay,
                                                         @RequestParam(value = "active" , required = false) Boolean active,
                                                         @RequestParam(value = "page" , required = false) Integer page,
                                                         @RequestParam(value = "limit" , required = false) Integer limit,
                                                         @RequestParam(value = "sortDir" , required = false) String sortDir,
                                                         @RequestParam(value = "orderBy" , required = false) String orderBy)
    {
        List<ImageDto> list = iImageService.findAll(gameId,active,activePlay,orderBy,sortDir, page,limit);
        return ResponseEntity.ok(list);
    }
}
