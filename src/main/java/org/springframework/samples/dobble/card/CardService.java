package org.springframework.samples.dobble.card;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.symbol.Symbol;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {
	
	private CardRepository cardRepository;
	
	@Autowired
	public CardService(CardRepository cardRepository){
		this.cardRepository = cardRepository;
	}

	@Transactional(readOnly = true)
	public List<Card> findAll() {
		return (List<Card>) cardRepository.findAll();
	}
	
	@Transactional
	public void deleteById(long id) {
		cardRepository.deleteById(id);
	}

	@Transactional
	public void save(Card mazo) {
		cardRepository.save(mazo);
	}

	@Transactional(readOnly = true)
	public Optional<Card> findById(long id) {
		return cardRepository.findById(id);
	}

	@Transactional
	public void resetSymbols() {
		Iterable<Card> mazos=cardRepository.findAll();
		List<Symbol> symbolsToBeRemoved=new ArrayList<>();
		for(Card mazo:mazos) {
			symbolsToBeRemoved.clear();
			for(Symbol symbol:mazo.getSymbols())
				symbolsToBeRemoved.add(symbol);
			mazo.getSymbols().removeAll(symbolsToBeRemoved);
			cardRepository.save(mazo);
		}
		
	}
	
}
