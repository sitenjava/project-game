package com.game.service.impl;

import com.game.common.MessageConstants;
import com.game.common.utils.Converter;
import com.game.common.exception.APIException;
import com.game.common.utils.SecurityUtils;
import com.game.data.dto.ImageDto;
import com.game.data.entities.Game;
import com.game.data.entities.Image;
import com.game.data.entities.User;
import com.game.data.repository.GameRepository;
import com.game.data.repository.ImageRepository;
import com.game.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ImageService implements IImageService
{
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private GameRepository gameRepository;
    private final Converter<ImageDto,Image> imageConverter = new Converter<>(ImageDto.class,Image.class);

    @Override
    @Transactional
    public ImageDto save(MultipartFile file , Integer gameId , Integer mapValue)
    {
        String url = "images/"+file.getOriginalFilename();
        Game game = gameRepository.findById(gameId).get();
        Image image = new Image(url,true,false,mapValue,game);
        return imageConverter.toDto(imageRepository.save(image));
    }

    @Override
    @Transactional
    public List<ImageDto> save(MultipartFile[] files , Integer gameId , Integer mapValue)
    {
        List<ImageDto> result = new ArrayList<>();
        for (MultipartFile file : files)
        {
            try {
                File writeFile = new File("src/main/resources/static/images/"+file.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(writeFile);
                byte[] bytes = file.getBytes();
                fos.write(bytes);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.add(save(file,gameId,mapValue));
        }
        return result;
    }

    @Override
    @Transactional
    public List<ImageDto> findAll(Integer gameId, Boolean active, Boolean activePlay,
                                       String orderBy, String sortDir, Integer page, Integer limit)
    {
        if (page == null || limit == null)
            throw APIException.from(HttpStatus.BAD_REQUEST).withMessage(MessageConstants.PAGE_AND_LIMIT_NOT_NULL);
        Pageable pageable = PageRequest.of(page-1,limit);
        Set<User> users = gameRepository.findUsersByGameId(gameId);
        if (!SecurityUtils.getInstance().isGamePlayer(users))
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
        if (orderBy != null && sortDir != null)
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageNumber(),
                    Sort.by(Sort.Direction.valueOf(sortDir),orderBy));
        List<Image> images = imageRepository.findAll(gameId,active,activePlay,pageable);
        if (images == null || images.isEmpty())
            throw APIException.from(HttpStatus.NOT_FOUND).withMessage(MessageConstants.IMAGE_NOT_FOUND);
        return imageConverter.toDto(images);
    }

    @Override
    @Transactional
    public List<ImageDto> findAllByActivePlay(Integer gameId) {
        Set<User> users = gameRepository.findUsersByGameId(gameId);
            if (!SecurityUtils.getInstance().isGamePlayer(users))
                throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
        List<Object> images = imageRepository.getLinkImages(gameId);
        if (images == null || images.isEmpty())
            throw  APIException.from(HttpStatus.NOT_FOUND).withMessage(MessageConstants.NOT_FOUND);
        List<ImageDto> result = new ArrayList<>();
        // just show id , link , mapValue for user
        for (Object image : images) {
            Object[] object = (Object[]) image;
            ImageDto imageDto = ImageDto.from((String) object[2])
                    .value((Integer) object[1])
                    .id((Integer) object[0]);
            result.add(imageDto);
        }
        return result;
    }

    // save list
    @Override
    @Transactional
    public void active(ImageDto imageDto)
    {
        Integer[] ids = imageDto.getIds();
        if (imageDto.getActive() != null)
        {
            for (Integer id : ids)
                imageRepository.updateActive(id,imageDto.getActive());
            return;
        }
        if (imageDto.getActivePlay() != null)
        {
            for (Integer id : ids)
                imageRepository.updateActivePlay(id,imageDto.getActivePlay());
        }
    }

    @Override
    @Transactional
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            String link = imageRepository.getLinkImage(id);
            File file = new File ("src/main/resources/static/"+link);
            boolean remove = file.delete();
            if (!remove)
                throw APIException.from(HttpStatus.NOT_ACCEPTABLE).withMessage(MessageConstants.IMAGE_REMOVE_FAILED);
            imageRepository.deleteById(id);
        }
    }
}
