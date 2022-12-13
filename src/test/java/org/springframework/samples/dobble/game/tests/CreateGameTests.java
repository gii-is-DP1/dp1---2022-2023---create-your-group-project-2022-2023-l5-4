package org.springframework.samples.dobble.game.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.game.GameService;
import org.springframework.samples.dobble.game.GameServiceTests;
import org.springframework.samples.dobble.user.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreateGameTests extends GameServiceTests{

     //Positive Tests:


     @Test
     public void createValidGame(){
         GameService gameService = new GameService(gameRepository, null, null, null);
         Game game= new Game();
         //Owner can't be Null and must have an username
         User owner = new User();
         owner.setUsername("adrgarpor");
 
         game.setOwner(owner);
 
         //Max. Number of players in range [2, 6]
         game.setMaxPlayers(6);
         
         //Gamemode can't be Null
         GameMode gameMode = new GameMode();
         gameMode.setName("The Tower");
 
         game.setGamemode(gameMode);
 
         //Saving and retrieving the new game and checking it equals the created one
         when(gameRepository.save(any(Game.class))).thenReturn(game);
         game.setId(1L);
         Game savedGame = gameService.saveGame(game);
         
         assertEquals(game, savedGame);
         
 
     }


    
}
