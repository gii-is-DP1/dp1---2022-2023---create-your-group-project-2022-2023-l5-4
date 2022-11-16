package org.springframework.samples.petclinic.tournament;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentService {
    
    @Autowired
    private TournamentRepository torneoRepo;

    @Transactional(readOnly = true)
    public Iterable<Tournament> getTorneos(){
        return torneoRepo.findAll();
    }

    @Transactional
    public Tournament getTournamentById(int id){
        Optional<Tournament> result=torneoRepo.findById(id);
        return result.isPresent()?result.get():null;
    }

    @Transactional
    public void save(Tournament t){
        torneoRepo.save(t);
    }
}
