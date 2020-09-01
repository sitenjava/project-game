package com.game.service;

import com.game.data.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService
{
    Image save(MultipartFile file , Integer gameId , Integer mapValue);
    List<Image> save(MultipartFile[] files , Integer gameId , Integer mapValue);
    void active(Integer[] ids , Boolean active);
    void delete(Integer[] ids);
}
