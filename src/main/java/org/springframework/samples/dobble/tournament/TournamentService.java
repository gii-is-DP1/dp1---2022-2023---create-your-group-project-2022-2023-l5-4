package org.springframework.samples.dobble.tournament;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentService {
	@Autowired
	private TournamentRepository mazoRepo;
	

	@Transactional(readOnly = true)
	public Iterable<Tournament> findAll() {
		return mazoRepo.findAll();
	}
	
	@Transactional
	public void deleteById(long id) {
		mazoRepo.deleteById(id);
	}

	@Transactional
	public void save(Tournament mazo) {
		mazoRepo.save(mazo);
	}

	@Transactional(readOnly = true)
	public Optional<Tournament> findById(long id) {
		return mazoRepo.findById(id);
	}

	@Transactional
	public void resetPlayers() {
		Iterable<Tournament> mazos=mazoRepo.findAll();
		List<Player> playersToBeRemoved=new ArrayList<>();
		for(Tournament mazo:mazos) {
			playersToBeRemoved.clear();
			for(Player player:mazo.getPlayers())
				playersToBeRemoved.add(player);
			mazo.getPlayers().removeAll(playersToBeRemoved);
			mazoRepo.save(mazo);
		}
		
	}
}
