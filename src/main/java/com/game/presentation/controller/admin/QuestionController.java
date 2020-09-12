package com.game.presentation.controller.admin;

import com.game.data.dto.QuestionDto;
import com.game.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/api/question")
public class QuestionController
{
    @Autowired
    private IQuestionService iQuestionService;
    @PostMapping("/")
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody QuestionDto questionDto)
    {
        return ResponseEntity.ok(iQuestionService.add(questionDto));
    }
}
