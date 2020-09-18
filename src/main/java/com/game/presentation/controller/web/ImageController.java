package com.game.presentation.controller.web;

import com.game.data.dto.ImageDto;
import com.game.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController(value = "apiOfUser")
@RequestMapping("/api/image")
public class ImageController
{
    @Autowired
    private IImageService iImageService;
    @GetMapping("/{gameId}")
    public ResponseEntity<List<ImageDto>> getImage(@PathVariable Integer gameId)
    {
        return ResponseEntity.ok(iImageService.findAllByActivePlay(gameId));
    }
}
