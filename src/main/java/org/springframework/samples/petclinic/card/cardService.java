package org.springframework.samples.petclinic.card;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private CardRepository cardRepo;

	@Autowired
	public CardService(CardRepository cardRepo){
		this.cardRepo=cardRepo;

	}
	@Transactional(readOnly = true)
	public List<Card> getCards(){
		return cardRepo.findAll();
				
	}
	@Transactional
	public void deleteCard(long id) {
		cardRepo.deleteById(id);

	}
	@Transactional(readOnly = true)
	public Optional<Card> getCardById(long id) {
		Optional<Card> result=cardRepo.findById(id);
		return result;

	}
	@Transactional
	public void save(Card card) {
		cardRepo.save(card);
	}
    
}
