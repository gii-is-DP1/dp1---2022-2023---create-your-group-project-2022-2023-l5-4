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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.dobble.game.Game;


import org.springframework.samples.dobble.game.GameUser;
import org.springframework.samples.dobble.game.GameUserPk;
import org.springframework.samples.dobble.game.GameUserService;

import org.springframework.samples.dobble.tournament.Tournament;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private UserRepository userRepository;
	private GameUserService gameUserService;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
    public void setMissionService( GameUserService gameUserService) {
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

    @Transactional(readOnly = true)
    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails ud = null;
        if (principal instanceof UserDetails) {
            ud = ((UserDetails) principal);
        }
        if (ud != null) {
            return findUser(ud.getUsername());
        } else {
            return new User();
        }
    }

	@Transactional
    public List<User> getFriends() {
        return getLoggedUser().getFriends();
    }
	
	@Transactional
    public void addFriend(String username) {
        User user = getLoggedUser();
		User friend = findUser(username);
        if(!getLoggedUser().getFriends().contains(findUser(username))){
            user.addFriend(findUser(username));
			friend.addFriend(findUser(user.username));
        }
        saveUser(user);
		saveUser(friend);
    }

    @Transactional
    public void removeFriend(String username) {
        User user = getLoggedUser();
        User friend = findUser(username);
        if (user.getFriends().contains(friend)) {
            user.removeFriend(friend);
            saveUser(user);
        }
    }

	@Transactional
    public Page<User> getFriendsPaged(Pageable page) {
        Integer limit = (int) page.getOffset() + page.getPageSize();
        limit = limit > getFriends().size() ? getFriends().size() : limit;
        return new PageImpl<>(getLoggedUser().getFriends().subList((int) page.getOffset(), limit), page, getLoggedUser().getFriends().size());
	}


	

}
