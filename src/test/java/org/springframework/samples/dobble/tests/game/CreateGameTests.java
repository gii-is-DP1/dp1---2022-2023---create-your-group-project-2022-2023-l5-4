package org.springframework.samples.dobble.tests.game;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.game.GameService;
import org.springframework.samples.dobble.game.GameState;
import org.springframework.samples.dobble.user.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreateGameTests extends GameServiceTest{
    
    @Test
    public void createValidGame(){
        GameService gameService = new GameService(gameRepository, null);
        Game game= new Game();
        User owner = new User();
        owner.setUsername("Jugador1");
        game.setOwner(owner);
        game.setMaxPlayers(6);
        GameState estado = GameState.LOBBY;
        game.setState(estado);
        GameMode gameMode1 = GameMode.THE_TOWER;
        game.setGamemode(gameMode1);

        when(gameRepository.save(any(Game.class))).thenReturn(game);
        game.setId(1L);
        Game savedGame = gameService.saveGame(game); 
        assertEquals(game, savedGame);
    }

    @Test
    public void createGameWithNoGamemode(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Game game= new Game();
        User owner = new User();
        owner.setUsername("Jugador1");
        game.setOwner(owner);
        game.setMaxPlayers(6);
        GameState estado = GameState.LOBBY;
        game.setState(estado);
        game.setGamemode(null);
        

        assertFalse(validator.validate(game).isEmpty(), "No se puede crear una partida sin modo de juego");
    }

    @Test
    public void createGameWithMaxPlayers7(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Game game= new Game();
        User owner = new User();
        owner.setUsername("Jugador1");
        game.setOwner(owner);
        game.setMaxPlayers(7);
        GameState estado = GameState.LOBBY;
        game.setState(estado);
        game.setGamemode(GameMode.THE_TOWER);

        assertFalse(validator.validate(game).isEmpty(), "El máximo número de jugadores es 6");
    }

    @Test
    public void createGameWithMaxPlayers1(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Game game= new Game();
        User owner = new User();
        owner.setUsername("Jugador1");
        game.setOwner(owner);
        game.setMaxPlayers(1);
        GameState estado = GameState.LOBBY;
        game.setState(estado);
        game.setGamemode(GameMode.THE_TOWER);

        assertFalse(validator.validate(game).isEmpty(), "El mínimo número de jugadores es 2");
        
    }
}
