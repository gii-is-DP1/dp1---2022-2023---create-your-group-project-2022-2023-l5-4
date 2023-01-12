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
	private GameUserRepository gameUserRepository;
	private UserRepository userRepository;
	private UserService userService;

	@Autowired
	public GameService(GameRepository gameRepository, GameUserRepository gameUserRepository, UserRepository userRepository, UserService userService) {
		this.gameRepository = gameRepository;
		this.userRepository = userRepository;
		this.userService = userService;
		this.gameUserRepository = gameUserRepository;
	}

	@Transactional(readOnly = true)
	public List<GameMode> findGameModes() throws DataAccessException {
		return gameRepository.findGameModes();
	}

	@Transactional(readOnly = true)
	public List<Game> findAllUnstartedGames()  throws DataAccessException {
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

	@Transactional
	public void addGameUser(Long gameId, String username, String accessCode) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameRepository.findById(gameId).orElse(null);
		User user = userRepository.findById(username).orElse(null);
		GameUser gameUser = new GameUser(user, game);

		if (game == null || user == null) throw new NullPointerException("Neither user or game can be null");

		if (!game.validAccessCode(accessCode)) throw new AuthException("Wrong Access Code");
		
		if (game.isFull()) throw new IllegalStateException("The game is already full");
		
		if (!game.hasStarted() && !game.getUsers().contains(gameUser)) {
			gameUserRepository.save(gameUser);
			userService.setCurrentGame(user, game);
			gameRepository.save(game);
		}

		
	}

	@Transactional
	public void addGameUserTournament(Long gameId, String username) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameRepository.findById(gameId).orElse(null);
		User user = userRepository.findById(username).orElse(null);
		GameUser gameUser = new GameUser(user, game);

		if (game == null || user == null) throw new NullPointerException("Neither user or game can be null");

		
		if (game.isFull()) throw new IllegalStateException("The game is already full");
		
	
		gameUserRepository.save(gameUser);
		userService.setCurrentGame(user, game);
		gameRepository.save(game);
	

		
	}


	@Transactional
	public void deleteUserGame(Long gameId, String username) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameRepository.findById(gameId).orElse(null);
		User user = userRepository.findById(username).orElse(null);
		GameUser gameUser = new GameUser(user, game);
		if (game == null || user == null) throw new NullPointerException("Neither user or game can be null");
	
	if (!game.hasStarted()) {
		game.removeUser(gameUser);
		gameUserRepository.save(gameUser);
		userService.setCurrentGame(user, game);
  	gameRepository.save(game);
 }	
}


}
