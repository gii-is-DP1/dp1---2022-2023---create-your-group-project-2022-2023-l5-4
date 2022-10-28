package org.springframework.samples.petclinic.card;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class cardService {
    private cardRepository cardRepo;

	@Autowired
	public cardService(cardRepository cardRepo){
		this.cardRepo=cardRepo;

	}
	@Transactional(readOnly = true)
	public List<card> getCards(){
		return cardRepo.findAll();
				
	}
	@Transactional
	public void deleteCard(long id) {
		cardRepo.deleteById(id);

	}
	@Transactional(readOnly = true)
	public Optional<card> getCardById(long id) {
		Optional<card> result=cardRepo.findById(id);
		return result;

	}
	@Transactional
	public void save(card card) {
		cardRepo.save(card);
	}
    
}
