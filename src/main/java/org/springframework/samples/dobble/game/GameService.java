package org.springframework.samples.dobble.game;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
r
	
	
    private GameRepository gameRepository;
	
	@Autowired
	public GameService(GameRepository gameRepository){
		this.gameRepository=gameRepository;
	}
	
	@Transactional(readOnly = true)
	public List<GameMode> findGameModes() throws DataAccessException {
		return gameRepository.findGameModes();
	}

	@Transactional(readOnly = true)
    public Game findGame(Long gameId) throws NoSuchElementException{
        return gameRepository.findById(gameId).get();
    }

	@Transactional(readOnly = true)
    public List<Game> findAllGames() throws NoSuchElementException{
        return gameRepository.findAll();
    }

	@Transactional
	public Game saveGame(Game game){
		return gameRepository.save(game);
	}

	@Transactional
	public void deleteGame(Game game){
		gameRepository.delete(game);
	}

	@Transactional
	public void deleteGameById(Long gameId){
		gameRepository.deleteById(gameId);
	}

}
