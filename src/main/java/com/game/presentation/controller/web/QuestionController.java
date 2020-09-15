package com.game.presentation.controller.web;

import com.game.service.IQuestionService;
import com.game.service.impl.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "restOfUser")
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private IQuestionService iQuestionService;

//    @GetMapping("/")
//    public ResponseEntity<List<Object>> q
}
