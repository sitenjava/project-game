package com.game.service.impl;

import com.game.common.converters.QuestionConverter;
import com.game.data.dto.QuestionDto;
import com.game.data.entities.Question;
import com.game.data.repository.QuestionRepository;
import com.game.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    private QuestionConverter questionConverter = QuestionConverter.getInstance();
    @Override
    @Transactional
    public QuestionDto add(QuestionDto questionDto) {
        Question question = questionConverter.toEntity(questionDto);
        return questionConverter.toDto(questionRepository.save(question));
    }
}
