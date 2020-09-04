package com.game.presentation.controller.web;

import com.game.data.dto.LogDto;
import com.game.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/log")
public class LogController
{
    @Autowired
    private ILogService iLogService;
    @GetMapping("/")
    public ResponseEntity<Set<LogDto>> getLogs(@RequestParam(value = "userid" , required = false) Integer userId)
    {
        Set<LogDto> logs = iLogService.findAllByUserId(userId);
        return ResponseEntity.ok(logs);
    }
    @DeleteMapping("/")
    public ResponseEntity<String> removeLog(@RequestParam(value = "userid" , required = false) Integer userId)
    {
        iLogService.delete(userId);
        return ResponseEntity.ok("Log is removed");
    }
    @PostMapping("/")
    public ResponseEntity<LogDto> addLog(@RequestBody LogDto logDto)
    {
        logDto = iLogService.save(logDto);
        return ResponseEntity.ok(logDto);
    }
}
