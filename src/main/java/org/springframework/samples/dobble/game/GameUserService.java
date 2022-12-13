package org.springframework.samples.dobble.game;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameUserService {

    
	private GameUserRepository gameUserRepository;
	



	@Autowired
	public GameUserService(GameUserRepository gameUserRepository) {
		this.gameUserRepository = gameUserRepository;
	}

    @Transactional	
	public void saveGameUser(GameUser gameUser){
		gameUserRepository.save(gameUser);
	}
	
    @Transactional
	public void saveGameUsers(List<GameUser> gameUsers) {
        gameUserRepository.saveAll(gameUsers);
	}
	

}
