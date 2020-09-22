package com.game.presentation.controller.web;

import com.game.data.dto.NotificationDto;
import com.game.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController(value = "NotificationApiOfUser")
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private INotificationService iNotificationService;
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationDto>> getNotification(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(iNotificationService.findAllByUserId(userId));
    }
    // đếm thông báo chưa đọc
    @GetMapping("/{userId}/count")
    public ResponseEntity<Integer> count(@PathVariable Integer userId)
    {
        return ResponseEntity.ok(iNotificationService.count(userId));
    }
    // Params : userId , active
    @PutMapping
    public void activeNotification(@RequestBody NotificationDto notificationDto)
    {
        iNotificationService.active(notificationDto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id)
    {
        iNotificationService.delete(id);
    }
}
