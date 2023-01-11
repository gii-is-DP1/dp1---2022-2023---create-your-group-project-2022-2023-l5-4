package org.springframework.samples.dobble.tournament;

import java.util.List;
import java.util.NoSuchElementException;

import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentService {

	private TournamentRepository tournamentRepository;
	private UserService userService;

	@Autowired
	public TournamentService(TournamentRepository tournamentRepository, UserService userService) {
		this.tournamentRepository = tournamentRepository;
		this.userService = userService;
	}

	@Transactional(readOnly = true)
	public List<Tournament> findAllUnstartedTournaments()  throws DataAccessException {
		return tournamentRepository.findAllUnstarted();
	}

	@Transactional(readOnly = true)
	public Tournament findTournament(Long tournamentId) throws NoSuchElementException {
		return tournamentRepository.findById(tournamentId).get();
	}

	@Transactional(readOnly = true)
	public List<Tournament> findAllTournaments() throws NoSuchElementException {
		return tournamentRepository.findAll();
	}

	@Transactional
	public Tournament saveTournament(Tournament tournament) {
		return tournamentRepository.save(tournament);
	}

	@Transactional
	public void deleteTournament(Tournament tournament) {
		tournamentRepository.delete(tournament);
	}

	@Transactional
	public void deleteTournamentById(Long tournamentId) {
		tournamentRepository.deleteById(tournamentId);
	}

	@Transactional
	public void addUserTournament(Long tournamentId, String username, String accessCode) throws AuthException, NullPointerException, IllegalStateException{
		Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
		User user = userService.findUser(username);

		if (tournament == null || user == null) throw new NullPointerException("Neither user or tournament can be null");

		if (!tournament.validAccessCode(accessCode)) throw new AuthException("Wrong Access Code");
		
		if (tournament.isFull()) throw new IllegalStateException("The tournament is already full");
		if (!tournament.hasStarted()) {
			tournament.addUser(user);
			userService.setCurrentTournament(user, tournament);
			tournamentRepository.save(tournament);
		}

		
	}

	@Transactional
	public void deleteUserTournament(Long tournamentId, String username) throws AuthException, NullPointerException, IllegalStateException{
		Tournament tournament = tournamentRepository.findById(tournamentId).orElse(null);
		User user = userService.findUser(username);

		if (tournament == null || user == null) throw new NullPointerException("Neither user or tournament can be null");
		
		if (!tournament.hasStarted()) {
			tournament.removeUser(user);
			userService.setCurrentTournament(user, tournament);
			tournamentRepository.save(tournament);
		}

		
	}

	

}
