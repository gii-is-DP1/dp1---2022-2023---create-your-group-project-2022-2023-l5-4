package org.springframework.samples.petclinic.game;
import org.springframework.beans.factory.annotation.Autowired;
public class GameService {
    private GameRepository gameRepository;
	

	@Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}
}
