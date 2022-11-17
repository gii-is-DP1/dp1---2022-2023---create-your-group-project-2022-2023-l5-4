
package org.springframework.samples.dobble.player;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player,Long> {

	Player findByName(String nombre);

}
