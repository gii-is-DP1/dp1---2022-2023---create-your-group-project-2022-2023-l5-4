package org.springframework.samples.petclinic.game;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {
	
	
    private GameRepository gameRepository;
	
	@Autowired
	public GameService(GameRepository gameRepository){
		this.gameRepository=gameRepository;
	}

    public Game findGame(Long gameId) throws NoSuchElementException{
        return gameRepository.findById(gameId).get();
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
