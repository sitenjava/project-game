package com.game.service.impl;

import com.game.data.entities.Game;
import com.game.data.entities.Image;
import com.game.data.repository.GameRepository;
import com.game.data.repository.ImageRepository;
import com.game.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService
{
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private GameRepository gameRepository;
    @Override
    @Transactional
    public Image save(MultipartFile file , Integer gameId , Integer mapValue)
    {
        String url = "resources/templates/images/"+file.getOriginalFilename();
        Game game = gameRepository.findById(gameId).get();
        Image image = new Image(url,true,mapValue,game);
        return imageRepository.save(image);
    }

    @Override
    @Transactional
    public List<Image> save(MultipartFile[] files , Integer gameId , Integer mapValue)
    {
        List<Image> result = new ArrayList<>();
        for (MultipartFile file : files)
        {
            try {
//                File writeFile = new File("/home/truong02_bp/Desktop/intern-java/project-game/src/main/resources/static/images/"+file.getOriginalFilename());
                File writeFile = new File("images/"+file.getOriginalFilename());
                System.out.println(writeFile.getAbsoluteFile());
                FileOutputStream fos = new FileOutputStream(writeFile);
                byte[] bytes = file.getBytes();
                fos.write(bytes);
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.add(save(file,gameId,mapValue));
        }
        return result;
    }

    @Override
    @Transactional
    public void active(Integer[] ids , Boolean active)
    {
        Image image = null;
        for (Integer id : ids)
        {
             image = imageRepository.findById(id).get();
             image.setActive(active);
             imageRepository.save(image);
        }
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            imageRepository.deleteById(id);
        }
    }
}
