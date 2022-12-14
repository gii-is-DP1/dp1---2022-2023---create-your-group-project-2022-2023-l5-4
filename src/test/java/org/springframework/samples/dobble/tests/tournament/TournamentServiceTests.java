package org.springframework.samples.dobble.tests.tournament;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.dobble.tournament.TournamentRepository;

public class TournamentServiceTests {

    @MockBean
    protected TournamentRepository tournamentRepository;
    
}
