package org.springframework.samples.dobble.game;

import java.util.List;
import java.util.NoSuchElementException;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameUserService {

    
	private GameService gameService;
	private UserService userService;
	private GameUserRepository gameUserRepository;

	@Autowired
	public GameUserService(GameService gameService, UserService userService, GameUserRepository gameUserRepository) {
		this.gameService = gameService;
		this.userService = userService;
		this.gameUserRepository = gameUserRepository;
	}

	@Transactional
	public void save(GameUser gameUser) {
		gameUserRepository.save(gameUser);
	}

	@Transactional(readOnly = true)
	public List<GameUser> findAll() {
		return gameUserRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Integer findTotalScoreByUsername(String username) {
		return gameUserRepository.findTotalScoreByUsername(username);
	}

	@Transactional(readOnly = true)
	public GameUser findById(GameUserPk gameUserId) throws NoSuchElementException {
		return gameUserRepository.findById(gameUserId)
			.orElseThrow(() -> new NoSuchElementException("GameUser with id" + gameUserId + "was not found"));
	}

	@Transactional
	private void remove(GameUser gameUser) {
		gameUserRepository.delete(gameUser);
	}



	@Transactional
	public void addGameUser(Long gameId, String username, String accessCode) throws AuthException, NullPointerException, IllegalStateException{
		Game game = gameService.findGame(gameId);
		User user = userService.findUser(username);
		
		GameUser gameUser = new GameUser(user, game);
		
		if (game.getGameUsers().contains(gameUser)) return;
		
		if (!game.validAccessCode(accessCode)) throw new AuthException("Wrong Access Code");
		
		if (game.isFull()) throw new IllegalStateException("The game is already full");
		
		if (!game.hasStarted()) {
			Game currentGame = user.getCurrentGame();
			if (currentGame!=null && !currentGame.isFinished()){
				deleteGameUser(currentGame.getId(), username);
			}
			save(gameUser); 
			userService.setCurrentGame(user, game);
		}

		
	}
 
	@Transactional
	public void deleteGameUser(Long gameId, String username)
			throws NoSuchElementException {
		Game game = gameService.findGame(gameId);

		if (game.hasStarted()) return;

		User user = userService.findUser(username);
		GameUser gameUser = findById(GameUserPk.of(user, game));
		game.getGameUsers().remove(gameUser);
		remove(gameUser);
		gameService.saveGame(game);
		userService.setCurrentGame(user, null);
			
		if (game.getNumUsers()==0) {
			gameService.deleteGame(game);
			return;
		}
		
		if (game.getOwner().equals(user)) gameService.chooseNewOwner(game);
	}



	public void makePlay(Game game, GameUser gameUser) {
		switch (game.getGamemode()) {
			case THE_TOWER:
				gameUser.getCards().add(game.getCurrentCard());
				game.nextCard();
				gameUser.addPoints(50);
				break;
			case THE_WELL:
				game.getCards().add(gameUser.getCurrentCard());
				gameUser.nextCard();
				gameUser.addPoints(50);
				break;
			case THE_POISONED_GIFT:
				gameUser.getCards().add(game.getCurrentCard());
				game.nextCard();
				GameUser actor = findById(GameUserPk.of(userService.getLoggedUser(), game));
				actor.addPoints(100);
				gameUser.substractPoints(50);
				save(actor);
				break;
			default:
				gameUser.getCards().add(game.getCurrentCard());
				game.nextCard();

		}

		gameService.saveGame(game);
		save(gameUser);
	}
}
