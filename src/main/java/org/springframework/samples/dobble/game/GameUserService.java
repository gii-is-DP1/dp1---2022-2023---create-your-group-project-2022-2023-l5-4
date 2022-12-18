package org.springframework.samples.dobble.game;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameUserService {

    
	private GameUserRepository gameUserRepository;
	

	@Autowired
	public GameUserService(GameUserRepository gameUserRepository) {
		this.gameUserRepository = gameUserRepository;
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

}
