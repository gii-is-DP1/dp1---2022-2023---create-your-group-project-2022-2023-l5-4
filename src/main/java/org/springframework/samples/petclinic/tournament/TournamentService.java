package org.springframework.samples.petclinic.tournament;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TournamentService {
    
    @Autowired
    private TournamentRepository torneoRepo;

    @Transactional(readOnly = true)
    public List<Tournament> getAllTournaments(){
        return torneoRepo.findAll();
    }

    @Transactional
    public void deleteTournament(int id){
        torneoRepo.deleteById((long) id);
    }

    @Transactional
    public Tournament getTournamentById(int id){
        Optional<Tournament> result=torneoRepo.findById((long) id);
        return result.isPresent()?result.get():null;
    }

    @Transactional
    public void save(Tournament t){
        torneoRepo.save(t);
    }
}
