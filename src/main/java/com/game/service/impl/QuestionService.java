package com.game.service.impl;

import com.game.common.MessageConstants;
import com.game.common.utils.Converter;
import com.game.common.exception.APIException;
import com.game.common.utils.SecurityUtils;
import com.game.data.dto.QuestionDto;
import com.game.data.entities.Question;
import com.game.data.entities.User;
import com.game.data.repository.QuestionRepository;
import com.game.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    private final Converter<QuestionDto,Question> questionConverter = new Converter<>(QuestionDto.class,Question.class);

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
    public List<QuestionDto> findAllByGameIdAndActive(Integer gameId , Boolean active) {
        List<Question> questions = questionRepository.findAllByGameIdAndActive(gameId,active);
        if (questions.isEmpty())
            throw APIException.from(HttpStatus.NOT_FOUND).withMessage(MessageConstants.QUESTION_NOT_FOUND);
        Set<User> users = questions.get(0).getGame().getUsers();
        if (!SecurityUtils.getInstance().isGamePlayer(users))
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
        List<QuestionDto> list = questionConverter.toDto(questions);
        // not show game info for users
        list.forEach(questionDto -> questionDto.setGame(null));
        return list;
    }

    @Override
    @Transactional
    public List<QuestionDto> findAllByGameId(Integer gameId, Integer page, Integer limit) {
        if (page == null || limit == null)
            throw APIException.from(HttpStatus.BAD_REQUEST).withMessage(MessageConstants.PAGE_AND_LIMIT_NOT_NULL);
        Pageable pageable = PageRequest.of(page-1,limit);
        List<Question> questions = questionRepository.findAllByGameId(gameId,pageable);
        if (questions.isEmpty())
            throw APIException.from(HttpStatus.NOT_FOUND).withMessage(MessageConstants.QUESTION_NOT_FOUND);
        return questionConverter.toDto(questions);
    }
}
