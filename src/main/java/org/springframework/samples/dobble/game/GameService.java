package org.springframework.samples.dobble.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserRepository;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

	private GameRepository gameRepository;
	private UserService userService;

	@Autowired
	public GameService(GameRepository gameRepository, UserService userService) {
		this.gameRepository = gameRepository;
		this.userService = userService;
	}

	@Transactional(readOnly = true)
	public List<GameMode> findGameModes() throws DataAccessException {
		return gameRepository.findGameModes();
	}

	@Transactional(readOnly = true)
	public List<Game> findAllUnstartedGames() throws DataAccessException {
		return gameRepository.findAllUnstarted();
	}

	@Transactional(readOnly = true)
	public Game findGame(Long gameId) throws NoSuchElementException {
		return gameRepository.findById(gameId).get();
	}

	@Transactional(readOnly = true)
	public List<Game> findAllGames() throws NoSuchElementException {
		return gameRepository.findAll();
	}

	@Transactional
	public Game saveGame(Game game) {
		return gameRepository.save(game);
	}

	@Transactional
	public void deleteGame(Game game) {
		gameRepository.delete(game);
	}

	@Transactional
	public void deleteGameById(Long gameId) {
		gameRepository.deleteById(gameId);
	}
	
}
