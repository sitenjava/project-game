package com.game.service.impl;

import com.game.common.converters.RankConverter;
import com.game.data.dto.RankDto;
import com.game.data.entities.Rank;
import com.game.data.repository.RankRepository;
import com.game.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RankService implements IRankService
{
    @Autowired
    private RankRepository rankRepository;
    public static RankConverter rankConverter = RankConverter.getInstance();
    @Override
    @Transactional
    public RankDto save(RankDto rankDto)
    {
        Rank rank = rankConverter.toEntity(rankDto);
        return rankConverter.toDto(rankRepository.save(rank));
    }

    @Override
    public List<RankDto> findAll(Integer gameId , Pageable pageable)
    {
        return rankConverter.toDto(rankRepository.findAllByGameId(gameId,pageable));
    }
}
