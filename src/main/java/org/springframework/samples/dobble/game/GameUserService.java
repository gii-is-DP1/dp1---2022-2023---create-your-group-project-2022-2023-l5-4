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

    
	private GameUserRepository gameUserRepository;
	private GameService gameService;
	private UserService userService;

	@Autowired
	public GameUserService(GameUserRepository gameUserRepository, GameService gameService, UserService userService) {
		this.gameUserRepository = gameUserRepository;
		this.gameService = gameService;
		this.userService = userService;
	}

	@Transactional(readOnly = true)
	public GameUser findGameUser(GameUserPk id){
		return gameUserRepository.findById(id).orElse(null);
	}

    @Transactional	
	public void saveGameUser(GameUser gameUser){
		gameUserRepository.save(gameUser);
	}
	
    @Transactional
	public void saveGameUsers(List<GameUser> gameUsers) {
        gameUserRepository.saveAll(gameUsers);
	}

	@Transactional
	public void delete(GameUser gameUser){
		gameUserRepository.delete(gameUser);
	}
	
	@Transactional
	public List<GameUser> findAll() throws DataAccessException {
		return gameUserRepository.findAll();
	}

	@Transactional
	public void addGameUser(Long gameId, String username, String accessCode) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameService.findGame(gameId);
		User user = userService.findUser(username);
		GameUser gameUser = new GameUser(user, game);

		if (game == null || user == null) throw new NullPointerException("Neither user or game can be null");

		if (!game.validAccessCode(accessCode)) throw new AuthException("Wrong Access Code");
		
		if (game.isFull()) throw new IllegalStateException("The game is already full");
		
		if (!game.hasStarted() && !game.getUsers().contains(gameUser)) {
			saveGameUser(gameUser);
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
		GameUser gameUser = findGameUser(GameUserPk.of(user, game));
		if (game == null || user == null)
			throw new NullPointerException("Neither user or game can be null");

		if (!game.hasStarted()) {
			delete(gameUser);
			userService.setCurrentGame(user, null);
		}
	}
	public void addGameUserTournament(Long gameId, String username) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameService.findGame(gameId);
		User user = userService.findUser(username);
		GameUser gameUser = new GameUser(user, game);

		if (game == null || user == null) throw new NullPointerException("Neither user or game can be null");

		
		if (game.isFull()) throw new IllegalStateException("The game is already full");
		
	
		System.out.println("ENTRA");
		saveGameUser(gameUser);
		userService.setCurrentGame(user, game);
		gameService.saveGame(game);	
	}


	public void makePlay(Game game, User user) {
		GameUser gameUser = findGameUser(GameUserPk.of(user, game));
		switch (game.getGamemode().getName()) {
			case "The Tower":
				gameUser.getHand().add(game.getHand().getCurrentCard());
				game.getHand().nextCard();
				break;
			case "The Well":
				game.getHand().add(gameUser.getHand().getCurrentCard());
				gameUser.getHand().nextCard();
				break;
			case "The Poisoned Gift":
				gameUser.getHand().add(game.getHand().getCurrentCard());
				game.getHand().nextCard();
				break;
			default:
				gameUser.getHand().add(game.getHand().getCurrentCard());
				game.getHand().nextCard();

		}

		game.setCentralDeck(game.getHand());
		gameUser.setCards(gameUser.getHand());

		saveGameUser(gameUser);
		gameService.saveGame(game);
	}
}
