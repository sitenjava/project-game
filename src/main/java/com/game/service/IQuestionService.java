package com.game.service;

import com.game.data.dto.QuestionDto;

import java.util.List;

public interface IQuestionService {
    QuestionDto add(QuestionDto questionDto);
    QuestionDto update(QuestionDto questionDto);
    void delete(QuestionDto questionDto);
    List<QuestionDto> findAllByGameIdAndActive(Integer gameId , Boolean active);
    List<QuestionDto> findAllByGameId(Integer gameId , Integer page , Integer limit);
}
