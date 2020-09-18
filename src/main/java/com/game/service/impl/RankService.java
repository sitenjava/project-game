package com.game.service.impl;

import com.game.common.MessageConstants;
import com.game.common.utils.Converter;
import com.game.common.exception.APIException;
import com.game.data.dto.RankDto;
import com.game.data.entities.Rank;
import com.game.data.repository.RankRepository;
import com.game.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RankService implements IRankService
{
    @Autowired
    private RankRepository rankRepository;
    private final Converter<RankDto,Rank> rankConverter = new Converter<>(RankDto.class,Rank.class);

    @Override
    @Transactional
    public RankDto save(RankDto rankDto)
    {
        Rank rank = rankConverter.toEntity(rankDto);
        return rankConverter.toDto(rankRepository.save(rank));
    }

    @Override
    public List<RankDto> findAll(Integer gameId, Integer page, Integer limit)
    {
        if (page == null || limit == null)
            throw APIException.from(HttpStatus.BAD_REQUEST).withMessage(MessageConstants.PAGE_AND_LIMIT_NOT_NULL);
        Pageable pageable = PageRequest.of(page-1,limit);
        return rankConverter.toDto(rankRepository.findAllByGameId(gameId,pageable));
    }
}
