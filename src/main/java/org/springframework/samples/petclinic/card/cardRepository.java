package org.springframework.samples.petclinic.card;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface cardRepository extends CrudRepository<card, Long> { 
    List<card> findAll();
    
}
