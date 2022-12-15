package org.springframework.samples.dobble.tournament;
import java.util.List;

import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.dobble.game.GameMode;

public interface TournamentRepository extends CrudRepository<Tournament,Long> {

    @Query("SELECT gamemode FROM GameMode gamemode ORDER BY gamemode.name")
    Iterable<GameMode> findGameModes() throws DataAccessException;

    @Query("SELECT tournament FROM Tournament tournament")
    List<Tournament> findAll() throws DataAccessException;

    @Query("SELECT tournament FROM Tournament tournament WHERE tournament.state='LOBBY'")
    List<Tournament> findAllUnstarted();
    

}
