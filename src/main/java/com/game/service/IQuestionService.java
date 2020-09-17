package com.game.service;

import com.game.data.dto.QuestionDto;
import com.game.data.entities.Question;

import java.util.List;
import java.util.Set;

public interface IQuestionService {
    QuestionDto add(QuestionDto questionDto);
    QuestionDto update(QuestionDto questionDto);
    void delete(QuestionDto questionDto);
    List<QuestionDto> findAllByGameIdAndActive(Integer gameId , Boolean active);
    List<QuestionDto> findAllByGameId(Integer gameId , Integer page , Integer limit);
}
