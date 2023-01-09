package org.springframework.samples.dobble.tests.game;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.game.GameUser;
import org.springframework.samples.dobble.user.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GameSetUpTests {

    @Test
    public void createGame() {
        Game game = new Game();
        GameUser user0 = new GameUser();
        GameUser user1 = new GameUser();
        GameUser user2 = new GameUser();
        GameUser user3 = new GameUser();
        game.setGameUsers(List.of(user0, user1, user2, user3));      
    }
    
}
