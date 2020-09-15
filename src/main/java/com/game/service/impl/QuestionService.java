package com.game.service.impl;

import com.game.common.MessageConstants;
import com.game.common.converters.QuestionConverter;
import com.game.common.exception.APIException;
import com.game.data.dto.QuestionDto;
import com.game.data.entities.Question;
import com.game.data.repository.QuestionRepository;
import com.game.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    private final QuestionConverter questionConverter = QuestionConverter.getInstance();
    @Override
    @Transactional
    public QuestionDto add(QuestionDto questionDto) {
        Question question = questionConverter.toEntity(questionDto);
        return questionConverter.toDto(questionRepository.save(question));
    }

    @Override
    @Transactional
    public QuestionDto update(QuestionDto questionDto) {
        Question question = questionConverter.toEntity(questionDto);
        return questionConverter.toDto(questionRepository.save(question));
    }

    @Override
    @Transactional
    public void delete(QuestionDto questionDto) {
        Integer[] ids = questionDto.getIds();
        Arrays.asList(ids).forEach(id -> {
            questionRepository.deleteById(id);
        });
    }

    @Override
    @Transactional
    public List<QuestionDto> findAll() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty())
            throw APIException.from(HttpStatus.NOT_FOUND).withMessage(MessageConstants.Question_Not_Found);
        return questionConverter.toDto(questions);
    }
}
