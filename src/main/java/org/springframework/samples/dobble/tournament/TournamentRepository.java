package org.springframework.samples.dobble.tournament;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long>{
    List<Tournament> findAll();
}