package com.game.presentation.controller.web;

import com.game.data.dto.QuestionDto;
import com.game.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "restOfUser")
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private IQuestionService iQuestionService;

    @GetMapping("/{gameId}")
    public ResponseEntity<List<QuestionDto>> getImage(@PathVariable Integer gameId)
    {
        return ResponseEntity.ok(iQuestionService.findAllByGameIdAndActive(gameId,true));
    }
}
