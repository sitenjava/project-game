package com.game.presentation.controller.web;

import com.game.data.dto.GameDto;
import com.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "GameApiOfUser")
@RequestMapping("/api/game")
public class GameController
{
    @Autowired
    private IGameService iGameService;

    @GetMapping
    public ResponseEntity<List<GameDto>> getList(@RequestParam(value = "categoryId" , required = false) Integer categoryId,
                                                 @RequestParam(value = "active" , required = false) Boolean active,
                                                 @RequestParam(value = "sortDir" , required = false) String sortDir,
                                                 @RequestParam(value = "orderBy", required = false) String orderBy,
                                                 @RequestParam(value = "page" , required = false) Integer page,
                                                 @RequestParam(value = "limit" , required = false) Integer limit)
    {
        List<GameDto> list = iGameService.findAll(categoryId,active,orderBy,sortDir,page,limit);
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGame(@PathVariable Integer id)
    {
        GameDto game = iGameService.findById(id);
        return ResponseEntity.ok(game);
    }
}
