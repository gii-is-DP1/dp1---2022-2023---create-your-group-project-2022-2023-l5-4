package org.springframework.samples.dobble.tests.game;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.user.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GameSetUpTests {

    @Test
    public void createGame() {
        Game game = new Game();
        User user0 = new User();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        game.setUsers(List.of(user0, user1, user2, user3));
        
    }
    
}
