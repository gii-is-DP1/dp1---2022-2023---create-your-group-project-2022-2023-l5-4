package org.springframework.samples.dobble.tournament;
import java.util.List;

import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.game.GameUser;

public interface TournamentRepository extends CrudRepository<Tournament,Long> {

    @Query("SELECT tournament FROM Tournament tournament")
    List<Tournament> findAll() throws DataAccessException;

    @Query("SELECT tournament FROM Tournament tournament WHERE tournament.state='LOBBY'")
    List<Tournament> findAllUnstarted();

    @Query("SELECT gu FROM GameUser gu WHERE gu.game.tournament.id = :tournamentId ORDER BY gu.score DESC")
    GameUser findHighestScoredPlayerInTournament(@Param("tournamentId") Long tournamentId);
    

}
