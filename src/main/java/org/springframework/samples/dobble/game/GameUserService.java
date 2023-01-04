package org.springframework.samples.dobble.game;

import java.util.List;

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
public class GameUserService {

    
	private GameService gameService;
	private UserService userService;

	@Autowired
	public GameUserService(GameService gameService, UserService userService) {
		this.gameService = gameService;
		this.userService = userService;
	}


	@Transactional
	public void addGameUser(Long gameId, String username, String accessCode) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameService.findGame(gameId);
		User user = userService.findUser(username);

		if (game == null || user == null) throw new NullPointerException("Neither user or game can be null");

		if (!game.validAccessCode(accessCode)) throw new AuthException("Wrong Access Code");
		
		if (game.isFull()) throw new IllegalStateException("The game is already full");
		
		if (!game.hasStarted() && !game.getUsers().contains(user)) {
			game.getUsers().add(user);
			gameService.saveGame(game);
			Game currentGame = user.getCurrentGame();
			if (currentGame!=null && !currentGame.isFinished()){
				deleteGameUser(currentGame.getId(), username);
			}
			userService.setCurrentGame(user, game);
		}

		
	}

	@Transactional
	public void deleteGameUser(Long gameId, String username)
			throws AuthException, NullPointerException, IllegalStateException {
		Game game = gameService.findGame(gameId);
		User user = userService.findUser(username);
		if (game == null || user == null)
			throw new NullPointerException("Neither user or game can be null");

		if (!game.hasStarted()) {
			game.getUsers().remove(user);
			gameService.saveGame(game);
			userService.setCurrentGame(user, null);
		}
	}
	public void addGameUserTournament(Long gameId, String username) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameService.findGame(gameId);
		User user = userService.findUser(username);

		if (game == null || user == null) throw new NullPointerException("Neither user or game can be null");

		
		if (game.isFull()) throw new IllegalStateException("The game is already full");
		
	
		game.getUsers().add(user);
		gameService.saveGame(game);
		userService.setCurrentGame(user, game);
		gameService.saveGame(game);	
	}


	public void makePlay(Game game, User user) {
		switch (game.getGamemode()) {
			case THE_TOWER:
				user.getCards().add(game.getCurrentCard());
				game.nextCard();
				break;
			case THE_WELL:
				game.getCards().add(user.getCurrentCard());
				user.nextCard();
				break;
			case THE_POISONED_GIFT:
				user.getCards().add(game.getCurrentCard());
				game.nextCard();
				break;
			default:
				user.getCards().add(game.getCurrentCard());
				game.nextCard();

		}

		gameService.saveGame(game);
		userService.saveUser(user);
	}
}
