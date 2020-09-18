package com.game.service.impl;


import com.game.common.utils.Converter;
import com.game.common.utils.TimeUtils;
import com.game.data.dto.NotificationDto;
import com.game.data.entities.Notification;
import com.game.data.repository.NotificationRepository;
import com.game.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    private final Converter<NotificationDto,Notification> converter = new Converter<>(NotificationDto.class,Notification.class);
    @Override
    @Transactional
    public NotificationDto add(NotificationDto notificationDto) {
        Notification notification = converter.toEntity(notificationDto);
        return converter.toDto(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public void active(NotificationDto notificationDto) {
        Integer id = notificationDto.getId();
        Boolean active = notificationDto.getActive();
        notificationRepository.active(active,id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        notificationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<NotificationDto> findAllByUserId(Integer userId) {
//        List<NotificationDto> notifications = notificationConverter.toDto(notificationRepository.findAllByUserId(userId));
        List<NotificationDto> notifications = converter.toDto(notificationRepository.findAllByUserId(userId));
        notifications.forEach(notificationDto -> {
            notificationDto.setUser(null);
        });
        long currentTime = System.currentTimeMillis();
        // set timeResolve
        notifications.forEach(notificationDto -> {
            long time = notificationDto.getCreatedTime().getTime();
            notificationDto.setTimeResolve(TimeUtils.getInstance().getTime(currentTime - time));
        });
        return notifications;
    }

    @Override
    public Integer count(Integer userId) {
        return notificationRepository.count(false,userId);
    }
}
