/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.dobble.user;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.dobble.game.Game;

import org.springframework.samples.dobble.game.GameRepository;
import org.springframework.samples.dobble.game.GameUser;
import org.springframework.samples.dobble.game.GameUserPk;
import org.springframework.samples.dobble.game.GameUserService;

import org.springframework.samples.dobble.tournament.Tournament;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;
	private GameUserService gameUserService;

	@Autowired
	public UserService(UserRepository userRepository, GameUserService gameUserService) {
		this.userRepository = userRepository;
		this.gameUserService = gameUserService;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		user.setEnabled(true);
		userRepository.save(user);
	}
	

	@Transactional(readOnly = true)
	public User findUser(String username) {
		return userRepository.findById(username).orElse(null);

	}

	@Transactional
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	@Transactional
    public void setCurrentGame(User user, Game game) {
		Game currentGame = user.getCurrentGame();
		if (currentGame!=null && !currentGame.isFinished()){
			GameUser gameUser = gameUserService.findGameUser(GameUserPk.of(user,currentGame));
			gameUserService.delete(gameUser);
		}
		user.setCurrentGame(game);
		userRepository.save(user);

    }

	@Transactional
    public void setCurrentTournament(User user, Tournament tournament) {
		Tournament currentTournament= user.getCurrentTournament();
		if (currentTournament!=null && !currentTournament.isFinished()){
			currentTournament.removeUser(user);
		}
		user.setCurrentTournament(tournament);
		userRepository.save(user);

    }

	public Optional<User> findUsername(String username) {
		return userRepository.findById(username);
	}

	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}


	

}
