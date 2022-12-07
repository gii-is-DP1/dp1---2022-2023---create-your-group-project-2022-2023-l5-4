package org.springframework.samples.dobble.tournament;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserRepository;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentService {
	
	@Autowired
	private TournamentRepository tourRepository;
	private UserRepository userRepository;
	private UserService userService;
	

	@Transactional(readOnly = true)
	public Iterable<Tournament> findAll() {
		return tourRepository.findAll();
	}
	
	@Transactional
	public void deleteById(long id) {
		tourRepository.deleteById(id);
	}

	@Transactional
	public void save(Tournament torneo) {
		tourRepository.save(torneo);
	}

	@Transactional(readOnly = true)
	public Optional<Tournament> findById(long id) {
		return tourRepository.findById(id);
	}

	@Transactional
	public void addUserTournament(Long tourId, String username, String constraseña) throws AuthException, NullPointerException {
		Tournament tour = tourRepository.findById(tourId).orElse(null);
		User user = userRepository.findById(username).orElse(null);

		if (tour == null || user==null) throw new NullPointerException("Neither user or game can be null");
		if (!tour.validAccessCode(constraseña)) throw new AuthException("Wrong Access Code");
		if (tour != null && user != null) {
			tour.addUser(user);
			userService.setCurrentTournament(user, tour);
			tourRepository.save(tour);
			

		}
	}

	@Transactional
	public void resetPlayers() {
		Iterable<Tournament> tours=tourRepository.findAll();
		List<User> usersToBeRemoved=new ArrayList<>();
		for(Tournament tour:tours) {
			usersToBeRemoved.clear();
			for(User user:tour.getUsers())
				usersToBeRemoved.add(user);
			tour.getUsers().removeAll(usersToBeRemoved);
			tourRepository.save(tour);
		}
		
	}
}
