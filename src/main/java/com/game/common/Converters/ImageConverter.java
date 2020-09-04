package com.game.common.Converters;

import com.game.data.dto.ImageDto;
import com.game.data.entities.Image;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ImageConverter
{
    private static ImageConverter converter = null;
    private ModelMapper modelMapper = new ModelMapper();

    public static ImageConverter getInstance()
    {
        if (converter == null)
            converter = new ImageConverter();
        return converter;
    }

    private ImageConverter()
    {

    }
    public ImageDto toDto(Image image)
    {
        ImageDto imageDto = modelMapper.map(image,ImageDto.class);
        return imageDto;
    }
    public Image toEntity(ImageDto imageDto)
    {
        Image image = modelMapper.map(imageDto,Image.class);
        return image;
    }
    public List<ImageDto> toDto(List<Image> images)
    {
        List<ImageDto> list = new ArrayList<>();
        if (images.size()==0)
            return null;
        for (Image image  : images) {
            list.add(toDto(image));
        }
        return list;
    }
    public List<Image> toEntity(List<ImageDto> imageDtos)
    {
        List<Image> list = new ArrayList<>();
        if (imageDtos.size()==0)
            return null;
        for (ImageDto imageDto : imageDtos) {
            list.add(toEntity(imageDto));
        }
        return list;
    }
}
