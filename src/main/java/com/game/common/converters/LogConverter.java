package com.game.common.converters;

import com.game.data.dto.LogDto;
import com.game.data.entities.Log;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

public class LogConverter
{
    private static LogConverter logConverter = null;
    private static ModelMapper modelMapper = new ModelMapper();

    public static LogConverter getInstance()
    {
        if (logConverter == null)
            logConverter = new LogConverter();
        return logConverter;
    }
    private LogConverter(){}
    public LogDto toDto(Log log)
    {
        return modelMapper.map(log,LogDto.class);
    }
    public Log toEntity(LogDto logDto)
    {
        return modelMapper.map(logDto,Log.class);
    }
    public Set<LogDto> toDto(Set<Log> logs)
    {
        Set<LogDto> dtos = new HashSet<>();
        if (logs == null || logs.isEmpty())
            return null;
        for (Log log : logs) {
            dtos.add(toDto(log));
        }
        return dtos;
    }
}
