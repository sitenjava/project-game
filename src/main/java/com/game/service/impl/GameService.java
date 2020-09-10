package com.game.service.impl;

import com.game.common.Converters.GameConverter;
import com.game.common.Converters.UserConverter;
import com.game.data.dto.GameDto;
import com.game.data.entities.Game;
import com.game.data.entities.User;
import com.game.data.repository.GameRepository;
import com.game.data.repository.UserRepository;
import com.game.service.IGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GameService implements IGameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    public static GameConverter gameConverter = GameConverter.getInstance();
    public static UserConverter userConverter = UserConverter.getInstance();


    @Override
    @Transactional
    public GameDto add(GameDto gameDto) {
        Game game = gameConverter.toEntity(gameDto);
        return gameConverter.toDto(gameRepository.save(game));
    }

    @Override
    @Transactional
    public GameDto update(GameDto gameDto) {
        Game game = gameConverter.toEntity(gameDto);
        Set<User> users = gameRepository.findById(gameDto.getId()).get().getUsers();
        if (users == null)
            users = new HashSet<>();
        String action = gameDto.getAction();
        Integer[] ids = gameDto.getIds();
        if (action.equals("add")) {
            for (Integer id : ids)
                users.add(userRepository.findById(id).get());
        } else if (action.equals("remove")) {
            for (Integer id : ids)
                users.removeIf(user -> user.getId() == id);
        }
        game.setUsers(users);
        game.setId(gameDto.getId());
        return gameConverter.toDto(gameRepository.save(game));
    }

    @Override
    public GameDto findById(Integer id) {
        return gameConverter.toDto(gameRepository.findById(id).orElse(null));
    }

    @Override
    public List<GameDto> findAll(Integer categoryId, Boolean active, String orderBy, String sortDir, Pageable pageable) {
        if (orderBy != null && sortDir != null)
            pageable = PageRequest.of(pageable.getPageNumber(), (int) pageable.getPageSize(), Sort.Direction.valueOf(sortDir), orderBy);
        return gameConverter.toDto(gameRepository.findAll(categoryId, active, pageable));
    }
}
