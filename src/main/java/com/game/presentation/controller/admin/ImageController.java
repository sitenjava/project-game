package com.game.presentation.controller.admin;

import com.game.data.dto.GameDto;
import com.game.data.dto.ParamDto;
import com.game.data.entities.Image;
import com.game.data.entities.Test;
import com.game.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/image/")
public class ImageController
{
    @Autowired
    private IImageService iImageService;
    @PostMapping("upload")
    public ResponseEntity<List<Image>> uploadImage(@RequestBody MultipartFile[] files,
                                                   @RequestParam("gameId") Integer gameId ,
                                                   @RequestParam("mapValue") Integer mapValue)
    {
        System.out.println(gameId);
        System.out.println(mapValue);
        List<Image> list = iImageService.save(files,gameId,mapValue); // Error path write image to resource
        return ResponseEntity.ok().header("List image add" , "List image add")
                .body(list);
    }
    @PutMapping("active")
    public ResponseEntity<Boolean> activeImage(@RequestBody ParamDto paramDto)
    {
        iImageService.active(paramDto.getIds(),paramDto.getActive());
        return ResponseEntity.ok(true);
    }
    @DeleteMapping("remove")
    public ResponseEntity<Boolean> removeImage(@RequestBody ParamDto paramDto)
    {
        iImageService.delete(paramDto.getIds());
        return ResponseEntity.ok(true);
    }
}
