package org.springframework.samples.dobble.tournament;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.User;
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
	public void resetUsers() {
		Iterable<Tournament> mazos=mazoRepo.findAll();
		List<User> usersToBeRemoved=new ArrayList<>();
		for(Tournament mazo:mazos) {
			usersToBeRemoved.clear();
			for(User user:mazo.getUsers())
				usersToBeRemoved.add(user);
			mazo.getUsers().removeAll(usersToBeRemoved);
			mazoRepo.save(mazo);
		}
		
	}
}
