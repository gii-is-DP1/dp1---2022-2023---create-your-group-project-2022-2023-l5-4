package org.springframework.samples.dobble.tests.tournament;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.tournament.Tournament;
import org.springframework.samples.dobble.tournament.TournamentRepository;
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
        owner.setUsername("antbarjim1");
        tour.setOwner(owner);
        tour.setMaxPlayers(4);

        GameMode gameMode = GameMode.THE_WELL;
        GameMode gameMode1 = GameMode.THE_TOWER;
        List<GameMode> res = new ArrayList<>();
        res.add(gameMode1);
        res.add(gameMode);
        tour.setGamemodes(res);

        when(tournamentRepository.save(any(Tournament.class))).thenReturn(tour);
        tour.setId(1L);
        Tournament savedTour = tournament.saveTournament(tour);

        assertEquals(tour, savedTour);

    }
}
