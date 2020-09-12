package com.game.service;

import com.game.data.dto.QuestionDto;
import com.game.data.entities.Question;

public interface IQuestionService {
    QuestionDto add(QuestionDto questionDto);
}
