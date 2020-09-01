package com.game.presentation.controller.admin;

import com.game.common.Converters.GameConverter;
import com.game.data.dto.GameDto;
import com.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game/")
public class GameController
{
    @Autowired
    private IGameService iGameService;
    @GetMapping("getList")
    public ResponseEntity<List<GameDto>> getList(@RequestParam(value = "categoryId" , required = false) Integer categoryId,
                                                 @RequestParam(value = "active" , required = false) Boolean active )
    {
        List<GameDto> list = GameConverter.getInstance().toDto(iGameService.findAllByCategoryId(categoryId,active));
        return ResponseEntity.ok().header("My list","Game list :")
                .body(list);
    }
}
