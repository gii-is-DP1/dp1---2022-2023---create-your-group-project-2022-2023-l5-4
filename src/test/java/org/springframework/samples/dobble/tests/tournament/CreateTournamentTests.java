package org.springframework.samples.dobble.tests.tournament;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.tournament.Tournament;
import org.springframework.samples.dobble.tournament.TournamentService;
import org.springframework.samples.dobble.user.User;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CreateTournamentTests extends TournamentServiceTests{
    
    @Test
    public void CreateCorrectTournament(){
        TournamentService tournament = new TournamentService(tournamentRepository, null, null);

        Tournament tour = new Tournament();
        User owner = new User();
        owner.setUsername("Jugador1");
        tour.setOwner(owner);
        tour.setMaxPlayers(4);
        tour.setAccessCode("5678");

        GameMode gameMode = GameMode.THE_WELL;
        GameMode gameMode1 = GameMode.THE_TOWER;
        GameMode gameMode2 = GameMode.THE_POISONED_GIFT;
        List<GameMode> res = new ArrayList<>();
        res.add(gameMode1);
        res.add(gameMode);
        res.add(gameMode2);
        tour.setGamemodes(res);

        when(tournamentRepository.save(any(Tournament.class))).thenReturn(tour);
        tour.setId(1L);
        Tournament savedTour = tournament.saveTournament(tour);

        assertEquals(tour, savedTour);

    }

    @Test
    public void CreateTournamentWithoutGamemodes(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Tournament tour = new Tournament();
        User owner = new User();
        owner.setUsername("Jugador1");
        tour.setOwner(owner);
        tour.setMaxPlayers(7);
        tour.setAccessCode("5678");
        GameMode gameMode = GameMode.THE_WELL;
        GameMode gameMode1 = GameMode.THE_TOWER;
        GameMode gameMode2 = GameMode.THE_POISONED_GIFT;
        List<GameMode> res = new ArrayList<>();
        res.add(gameMode1);
        res.add(gameMode);
        res.add(gameMode2);
        tour.setGamemodes(res);
        tour.setGamemodes(null);

        assertFalse(validator.validate(tour).isEmpty(), "El número máximo de jugadores es 6");
    }
}
