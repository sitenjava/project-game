package com.game.presentation.controller.admin;

import com.game.data.dto.GameDto;
import com.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/game")
public class GameController
{
    @Autowired
    private IGameService iGameService;

    // Create game without users : params : name , description , timeLimit , active , url , isPublic , startTime , endTime
    @PostMapping
    public ResponseEntity<GameDto> addGame(@RequestBody GameDto gameDto)
    {
        GameDto result = iGameService.add(gameDto);
        return ResponseEntity.ok(result);
    }
    // Active cho player : params : Ids of user , id of Game , action : "add" or "remove"
    @PutMapping
    public ResponseEntity<GameDto> updateGame(@RequestBody GameDto gameDto)
    {
        GameDto game = iGameService.update(gameDto);
        return ResponseEntity.ok(game);
    }
}
