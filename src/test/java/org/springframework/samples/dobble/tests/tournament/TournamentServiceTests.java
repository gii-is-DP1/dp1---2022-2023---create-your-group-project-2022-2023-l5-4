package org.springframework.samples.dobble.tests.tournament;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.dobble.game.GameRepository;
import org.springframework.samples.dobble.tournament.TournamentRepository;
import org.springframework.samples.dobble.user.UserRepository;
import org.springframework.samples.dobble.user.UserService;

public class TournamentServiceTests {

    @MockBean
    protected TournamentRepository tournamentRepository;
    
    
    private UserRepository userRepository;
	private GameRepository gameRepository;
	private UserService userService;
}
