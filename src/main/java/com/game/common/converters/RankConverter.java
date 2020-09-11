package com.game.common.Converters;

import com.game.data.dto.RankDto;
import com.game.data.entities.Rank;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class RankConverter
{
    private static RankConverter rankConverter = null;
    private ModelMapper modelMapper = new ModelMapper();

    public static RankConverter getInstance()
    {
        if (rankConverter == null)
            rankConverter = new RankConverter();
        return rankConverter;
    }
    private RankConverter(){}

    public RankDto toDto(Rank rank)
    {
        return modelMapper.map(rank,RankDto.class);
    }
    public Rank toEntity(RankDto rankDto)
    {
        return modelMapper.map(rankDto,Rank.class);
    }
    public List<RankDto> toDto(List<Rank> ranks)
    {
        List<RankDto> result = new ArrayList<>();
        if (ranks.size()==0)
            return null;
        for (Rank rank : ranks) {
            result.add(toDto(rank));
        }
        return result;
    }
}
