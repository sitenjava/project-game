package com.game.common.converters;

import com.game.data.dto.QuestionDto;
import com.game.data.entities.Question;
import org.modelmapper.ModelMapper;

public class QuestionConverter {
    private ModelMapper modelMapper = new ModelMapper();
    private static QuestionConverter questionConverter = null;

    private QuestionConverter(){}

    public static QuestionConverter getInstance()
    {
        if (questionConverter == null)
            questionConverter = new QuestionConverter();
        return questionConverter;
    }
    public QuestionDto toDto(Question question)
    {
        return modelMapper.map(question , QuestionDto.class);
    }
    public Question toEntity(QuestionDto questionDto)
    {
        return modelMapper.map(questionDto , Question.class);
    }
}
