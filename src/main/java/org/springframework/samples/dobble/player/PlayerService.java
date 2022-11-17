
package org.springframework.samples.dobble.player;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepo;
	

	@Transactional(readOnly = true)
	public Iterable<Player> findAll() {
		return playerRepo.findAll();
	}

	@Transactional
	public void save(Player player) {
		playerRepo.save(player);
	}

	@Transactional(readOnly = true)
	public Optional<Player> findById(long id) {
		return playerRepo.findById(id);
	}
	@Transactional(readOnly = true)
	public Player findByNombre(String nombre) {
		return playerRepo.findByName(nombre);
	}
}
