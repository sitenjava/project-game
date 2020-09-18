package com.game.service;

import com.game.data.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService
{
    ImageDto save(MultipartFile file , Integer gameId , Integer mapValue);
    List<ImageDto> save(MultipartFile[] files , Integer gameId , Integer mapValue);
    List<ImageDto> findAll(Integer gameId, Boolean active , Boolean activePlay ,
                                String orderBy , String sortDir , Integer page , Integer limit);
//    List<ImageDto> findAllByActivePlay(Integer gameId);
    List<ImageDto> findAllByActivePlay(Integer gameId);
    void active(ImageDto imageDto);
    void delete(Integer[] ids);
}
