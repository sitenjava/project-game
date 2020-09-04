package com.game.common.Converters;

import com.game.data.dto.UserDto;
import com.game.data.entities.User;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UserConverter
{
    private static UserConverter userConverter = null;
    private static ModelMapper modelMapper = new ModelMapper();
    public static UserConverter getInstance()
    {
        if (userConverter == null)
            userConverter = new UserConverter();
        return userConverter;
    }
    private UserConverter(){}
    public UserDto toDto(User user)
    {
        UserDto userDto = modelMapper.map(user,UserDto.class);
        return userDto;
    }
    public User toEntity(UserDto userDto)
    {
        User user = modelMapper.map(userDto,User.class);
        return user;
    }
    public List<UserDto> toDto(List<User> users)
    {
        List<UserDto> list = new ArrayList<>();
        if (users == null)
            return null;
        for (User user : users) {
            list.add(toDto(user));
        }
        return list;
    }
    public List<User> toEntity(List<UserDto> users)
    {
        List<User> list = new ArrayList<>();
        if (users == null)
            return null;
        for (UserDto user : users) {
            list.add(toEntity(user));
        }
        return list;
    }
}
