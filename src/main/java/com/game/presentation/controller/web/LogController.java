package com.game.presentation.controller.web;

import com.game.data.dto.LogDto;
import com.game.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/log")
public class LogController
{
    @Autowired
    private ILogService iLogService;
    @GetMapping
    public ResponseEntity<List<LogDto>> getLogs(@RequestParam(value = "userid" , required = false) Integer userId ,
                                                @RequestParam(value = "gameId" , required = false) Integer gameId)
    {
        List<LogDto> logs = iLogService.findAllByUserIdAndGameId(userId,gameId);
        return ResponseEntity.ok(logs);
    }
    @DeleteMapping
    public ResponseEntity<String> removeLog(@RequestParam(value = "userid" , required = false) Integer userId ,
                                            @RequestParam(value = "gameId" , required = false) Integer gameId)
    {
        iLogService.delete(userId,gameId);
        return ResponseEntity.ok("Log is removed");
    }
    @PostMapping
    public ResponseEntity<LogDto> addLog(@RequestBody LogDto logDto)
    {
        logDto = iLogService.save(logDto);
        return ResponseEntity.ok(logDto);
    }
}
