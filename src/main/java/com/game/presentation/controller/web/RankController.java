package com.game.presentation.controller.web;

import com.game.data.dto.RankDto;
import com.game.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/rank")
public class RankController
{
    @Autowired
    private IRankService iRankService;
    @GetMapping("/")
    public ResponseEntity<List<RankDto>> getRanks(@RequestParam(value = "gameId" , required = false) Integer gameId,
                                                 @RequestParam(value = "page") Integer page,
                                                 @RequestParam(value = "limit") Integer limit)
    {
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by(Sort.Direction.ASC,"time"));
        List<RankDto> ranks = iRankService.findAll(gameId,pageable);
        return ResponseEntity.ok(ranks);
    }
    // params : time or score , Object user with id , Object game with id
    @PostMapping("/")
    public ResponseEntity<RankDto> addRank(@RequestBody RankDto rankDto)
    {
        RankDto rank = iRankService.save(rankDto);
        return ResponseEntity.ok(rank);
    }
}
