package com.game.service;

import com.game.data.dto.NotificationDto;

import java.util.List;

public interface INotificationService {
    NotificationDto add(NotificationDto notificationDto);
    void active(NotificationDto notificationDto);
    void delete(Integer id);
    List<NotificationDto> findAllByUserId(Integer userId);
    Integer count(Integer userId);
}
