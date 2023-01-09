package org.springframework.samples.dobble.tests.game;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javax.security.auth.message.AuthException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.game.GameService;
import org.springframework.samples.dobble.game.GameState;
import org.springframework.samples.dobble.game.GameUserService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.samples.dobble.user.User;
import org.springframework.samples.dobble.user.UserService;

@RunWith(SpringRunner.class)
public class JoinGameTests extends GameServiceTest{

    @Test
    public void joinGameValidCode(){

       
        UserService userService = new UserService(userRepository);
        User owner = new User();
        owner.setUsername("Jugador1");
        owner.setEmail("abcde@gmail.com");
        owner.setPassword("asd123");
        when(userRepository.save(any(User.class))).thenReturn(owner);
        userService.saveUser(owner);

        GameService gameService = new GameService(gameRepository, null);
        //GameUserService gameUserService = new GameUserService(gameService, userService);
        Game game= new Game();
        game.setOwner(owner);
        game.setMaxPlayers(6);
        GameState estado = GameState.LOBBY;
        game.setState(estado);
        GameMode gameMode1 = GameMode.THE_TOWER;
        game.setGamemode(gameMode1);
        game.setAccessCode("12345");
        game.setId(23L);

        when(gameRepository.save(any(Game.class))).thenReturn(game);
        Game savedGame = gameService.saveGame(game); 

        
        

        //try{
        //    gameUserService.addGameUser(savedGame.getId(), owner.getUsername(), "12345");
        //}catch(NullPointerException e){
        //    fail("Neither user or game can be null", e);
        //}catch(AuthException e){
        //    fail("Wrong Access Code", e);
        //}catch(IllegalStateException e){
        //    fail("The game is already full");
        //}
        //assertTrue(game.getNumUsers()==1, "Se ha unido correctamente");
        
    }
    
}
