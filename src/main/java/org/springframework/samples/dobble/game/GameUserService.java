package org.springframework.samples.dobble.game;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.dao.DataAccessException;
=======
>>>>>>> 4473549f5febb4ff4e17003273780c7e24408717
import org.springframework.stereotype.Service;

@Service
public class GameUserService {

    
	private GameUserRepository gameUserRepository;
	
<<<<<<< HEAD
=======



>>>>>>> 4473549f5febb4ff4e17003273780c7e24408717
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
<<<<<<< HEAD
	@Transactional
	public List<GameUser> findAll() throws DataAccessException {
		return gameUserRepository.findAll();
	}
=======
	
>>>>>>> 4473549f5febb4ff4e17003273780c7e24408717

}
