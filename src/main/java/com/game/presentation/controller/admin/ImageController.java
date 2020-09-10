package com.game.presentation.controller.admin;

import com.game.data.dto.ImageDto;
import com.game.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/api/image")
public class ImageController
{
    @Autowired
    private IImageService iImageService;

    // params : Images , Id of Game , mapValue of image
    @PostMapping("/")
    public ResponseEntity<List<ImageDto>> uploadImage(@RequestBody MultipartFile[] files,
                                                   @RequestParam("gameId") Integer gameId ,
                                                   @RequestParam("mapValue") Integer mapValue)
    {
        List<ImageDto>list = iImageService.save(files,gameId,mapValue); // Error path write image to resource

        return ResponseEntity.ok(list);
    }
    // params : Ids of images  , active or activePlay
    @PutMapping("/")
    public ResponseEntity<String> activeImage(@RequestBody ImageDto imageDto)
    {
        iImageService.active(imageDto);
        return ResponseEntity.ok("Image is actived");
    }
    // params : Ids of images
    @DeleteMapping("/")
    public ResponseEntity<String> removeImage(@RequestBody ImageDto imageDto)
    {
        iImageService.delete(imageDto.getIds());
        return ResponseEntity.ok("Remove is success");
    }
}
