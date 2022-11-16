package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Long> {
    List<Game> findAll();
}
