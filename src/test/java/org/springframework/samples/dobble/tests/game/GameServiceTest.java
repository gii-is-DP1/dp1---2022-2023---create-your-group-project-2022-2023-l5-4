package org.springframework.samples.dobble.tests.game;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.dobble.game.GameRepository;
import org.springframework.samples.dobble.user.UserRepository;

public class GameServiceTest {
    
    @MockBean
    protected GameRepository gameRepository;
    @MockBean
    protected UserRepository userRepository;


}
