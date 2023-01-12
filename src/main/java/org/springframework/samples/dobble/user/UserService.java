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


import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.resource.spi.IllegalStateException;
import javax.security.auth.message.AuthException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameRepository;
import org.springframework.samples.dobble.tournament.Tournament;
import org.springframework.samples.dobble.tournament.TournamentRepository;
import org.springframework.samples.dobble.tournament.TournamentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private UserRepository userRepository;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	
	@Transactional
	public void saveUser(User user) throws DataAccessException {
		userRepository.save(user);
	}
	

	@Transactional(readOnly = true)
	public User findUser(String username) throws NoSuchElementException {
		return userRepository.findById(username)
			.orElseThrow(() -> new NoSuchElementException("User with id" + username + "was not found"));
	}

    @Transactional List<User> getUserss(){
        return userRepository.findAll();
    }

	@Transactional
	public Iterable<User> getUsers(Pageable pageable) throws NoSuchElementException {
		return userRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public int userCount(){
		return (int) userRepository.count();
	}

	@Transactional
    public void setCurrentGame(User user, Game game) {
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
        Object principal = SecurityContextHolder.getContext().getAuthentication()
		.getPrincipal();
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




	@Transactional
    public List<Integer> calculatePages(Integer pageNumber) { 
        
        if(pageNumber == null){
            pageNumber = 0;
        }

        Integer userCount = userCount();
        Integer totalPages;

        if(userCount %5 == 0) {
            totalPages = userCount/5;
        } else {
            totalPages = userCount/5 + 1;
        }

        List<Integer> pages = new ArrayList<>();
        pages.add(0);
        pages.add(0);

        
        if(pageNumber==0){  //first page

            pages.set(0, pageNumber);
            pages.set(1, pageNumber+1);
        
        }else if(pageNumber==totalPages-1){  //last page
            pages.set(0, pageNumber-1);
            pages.set(1, pageNumber);

        } else { //intermediate pages
            pages.set(0, pageNumber-1);
            pages.set(1, pageNumber+1);

        }

        return pages;
    }

	@Transactional
	public List<User> getPaginatedUsers(Integer pageNumber){
		if(pageNumber==null){
			pageNumber=0;
		}
		Pageable page = PageRequest.of(pageNumber, 5);
		Iterable<User> usersPaginated = getUsers(page);
		return StreamSupport.stream(usersPaginated.spliterator(), false).collect(Collectors.toList());
	}

    @Transactional
    public void delete(User user){
        userRepository.delete(user);
    }






	

}
