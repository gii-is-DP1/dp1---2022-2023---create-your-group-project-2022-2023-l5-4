package org.springframework.samples.dobble.game;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

	@Autowired
    private GameRepository gameRepo;

    @Transactional(readOnly = true)
    public Iterable<Game> findAll(){
        return gameRepo.findAll();
    }

	@Transactional(readOnly = true)
	public Optional<Game> findById(Long idGame) {
		return gameRepo.findById(idGame);
	}

	@Transactional
    public void save(Game g){
        gameRepo.save(g);
    }
}
