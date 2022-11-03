package org.springframework.samples.dobble.card;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cardService {
    private cardRepository salaRepo;

	@Autowired
	public cardService(cardRepository salaRepo){
		this.salaRepo=salaRepo;

	}
	@Transactional(readOnly = true)
	public List<card> getCards(){
		return salaRepo.findAll();
				
	}
	@Transactional
	public void deleteCard(long id) {
		salaRepo.deleteById(id);

	}
	@Transactional(readOnly = true)
	public Optional<card> getCardById(long id) {
		Optional<card> result=salaRepo.findById(id);
		return result;

	}
	@Transactional
	public void save(card card) {
		salaRepo.save(card);
	}
    
}
