package com.game.presentation.controller.admin;

import com.game.data.dto.QuestionDto;
import com.game.data.entities.Question;
import com.game.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/api/question")
public class QuestionController
{
    @Autowired
    private IQuestionService iQuestionService;
    @GetMapping("/")
    public ResponseEntity<List<QuestionDto>> getQuestions()
    {
        return ResponseEntity.ok(iQuestionService.findAll());
    }
    @PostMapping("/")
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody QuestionDto questionDto)
    {
        return ResponseEntity.ok(iQuestionService.add(questionDto));
    }
    @PutMapping("/")
    public ResponseEntity<QuestionDto> updateQuestion(@RequestBody QuestionDto questionDto)
    {
        return ResponseEntity.ok(iQuestionService.update(questionDto));
    }
    @DeleteMapping("/")
    public void deleteQuestion(@RequestBody QuestionDto questionDto)
    {
        iQuestionService.delete(questionDto);
    }
}
