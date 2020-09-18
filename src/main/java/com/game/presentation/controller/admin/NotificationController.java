package com.game.presentation.controller.admin;

import com.game.data.dto.NotificationDto;
import com.game.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/notification")
public class NotificationController {
    @Autowired
    private INotificationService iNotificationService;
    @PostMapping
    public ResponseEntity<NotificationDto> add(@RequestBody NotificationDto notificationDto)
    {
        return ResponseEntity.ok(iNotificationService.add(notificationDto));
    }
}
