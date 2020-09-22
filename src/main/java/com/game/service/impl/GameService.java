package com.game.service.impl;

import com.game.common.MessageConstants;
import com.game.common.utils.Converter;
import com.game.common.exception.APIException;
import com.game.common.utils.SecurityUtils;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService implements IGameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;
    private final Converter<GameDto,Game> gameConverter = new Converter<>(GameDto.class,Game.class);

    private final SecurityUtils securityUtils = SecurityUtils.getInstance();

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
        if (action == null)
            throw APIException.from(HttpStatus.BAD_REQUEST).withMessage(MessageConstants.BAD_REQUEST);
        if (action.equals("add")) {
            for (Integer id : ids) {
                users.add(userRepository.findById(id).orElseThrow(APIException::new));
            }
        } else if (action.equals("remove")) {
            for (Integer id : ids)
                users.removeIf(user -> user.getId() == id);
        }
        game.setUsers(users);
        game.setId(gameDto.getId());
        return gameConverter.toDto(gameRepository.save(game));
    }

    @Override
    @Transactional
    public GameDto findById(Integer id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game == null)
            throw APIException.from(HttpStatus.NOT_FOUND).withMessage(MessageConstants.GAME_NOT_FOUND);
        GameDto gameDto = gameConverter.toDto(game);
        if (securityUtils.isAnonymous())
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.LOGIN_TO_PLAY);
        if (!SecurityUtils.getInstance().isPlayer(gameDto.getUsers()))
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.NOT_GAMER);
        // not show list user for user
        if (!SecurityUtils.getInstance().isAdmin())
            gameDto.setUsers(null);
        return gameDto;
    }

    @Override
    @Transactional
    public List<GameDto> findAll(Integer categoryId, Boolean active, String orderBy, String sortDir, Integer page, Integer limit) {
        if (securityUtils.isAnonymous())
            throw APIException.from(HttpStatus.FORBIDDEN).withMessage(MessageConstants.LOGIN_TO_PLAY);
        if (page == null || limit == null)
            throw APIException.from(HttpStatus.BAD_REQUEST).withMessage(MessageConstants.PAGE_AND_LIMIT_NOT_NULL);
        Pageable pageable = PageRequest.of(page - 1, limit);
        if (orderBy != null && sortDir != null)
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.valueOf(sortDir), orderBy);
        List<Game> games = gameRepository.findAll(categoryId, active, pageable);
        if (games.isEmpty())
            throw APIException.from(HttpStatus.NOT_FOUND).withMessage(MessageConstants.GAME_NOT_FOUND);
        List<GameDto> list = gameConverter.toDto(games);
        if (!securityUtils.isAdmin())
            list = list.stream().filter(game ->
                    securityUtils.isPlayer(game.getUsers())
            ).peek(game -> game.setUsers(null)).collect(Collectors.toList());
        return list;
    }
}
