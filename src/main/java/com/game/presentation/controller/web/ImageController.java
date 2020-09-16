package com.game.presentation.controller.web;

import com.game.data.dto.ImageDto;
import com.game.data.entities.Image;
import com.game.service.IImageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
