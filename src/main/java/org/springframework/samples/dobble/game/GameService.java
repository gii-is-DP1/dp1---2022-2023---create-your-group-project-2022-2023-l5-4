package org.springframework.samples.dobble.game;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

	private GameRepository gameRepository;

	@Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Transactional(readOnly = true)
	public List<Game> findAllUnstartedGames() throws DataAccessException {
		return gameRepository.findAllUnstarted();
	}

	@Transactional(readOnly = true)
	public Game findGame(Long gameId) throws NoSuchElementException {
		return gameRepository.findById(gameId)
			.orElseThrow(() -> new NoSuchElementException("Game with id '%s' was not found".formatted(gameId)));
	}

	@Transactional(readOnly = true)
	public List<Game> findAllGames() throws NoSuchElementException {
		return gameRepository.findAll();
	}

	@Transactional
	public Game saveGame(Game game) {
		game.setUpdatedAt(LocalDateTime.now());
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

    public void endGame(Game game, User winner) {
		game.setWinner(winner);
		game.setState(GameState.FINISHED);
		saveGame(game);
    }

	@Transactional
    public void chooseNewOwner(Game game) {
		if (game.getNumUsers() == 0) return; 
		game.setOwner(game.getGameUsers().get(0).getUser());
		saveGame(game);
	}
	
}
