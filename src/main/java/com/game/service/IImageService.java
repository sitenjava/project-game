package com.game.service;

import com.game.data.dto.ImageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService
{
    ImageDto save(MultipartFile file , Integer gameId , Integer mapValue);
    List<ImageDto> save(MultipartFile[] files , Integer gameId , Integer mapValue);
    List<ImageDto> findAll(Integer gameId, Boolean active , Boolean activePlay ,
                           String orderBy , String sortDir , Pageable pageable);
    void active(ImageDto imageDto);
    void delete(Integer[] ids);
}
