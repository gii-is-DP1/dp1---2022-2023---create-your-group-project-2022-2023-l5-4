package org.springframework.samples.dobble.game;

import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game,Long> {
    
}
