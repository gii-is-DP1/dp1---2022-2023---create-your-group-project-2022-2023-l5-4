package org.springframework.samples.dobble.game;

import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Long> {

    @Query("SELECT gamemode FROM GameMode gamemode ORDER BY gamemode.name")
    List<GameMode> findGameModes() throws DataAccessException;

    @Query("SELECT game FROM Game game")
    List<Game> findAll() throws DataAccessException;

    @Query("SELECT game FROM Game game WHERE game.state='LOBBY'")
    List<Game> findAllUnstarted();
    

}
