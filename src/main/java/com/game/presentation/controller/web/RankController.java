package com.game.presentation.controller.web;

import com.game.data.dto.RankDto;
import com.game.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rank")
public class RankController
{
    @Autowired
    private IRankService iRankService;
    @GetMapping
    public ResponseEntity<List<RankDto>> getRanks(@RequestParam(value = "gameId" , required = false) Integer gameId,
                                                 @RequestParam(value = "page" , required = false) Integer page,
                                                 @RequestParam(value = "limit" , required = false) Integer limit)
    {
        List<RankDto> ranks = iRankService.findAll(gameId,page,limit);
        return ResponseEntity.ok(ranks);
    }
    // params : time or score , Object user with id , Object game with id
    @PostMapping
    public ResponseEntity<RankDto> addRank(@RequestBody RankDto rankDto)
    {
        RankDto rank = iRankService.save(rankDto);
        return ResponseEntity.ok(rank);
    }
}
