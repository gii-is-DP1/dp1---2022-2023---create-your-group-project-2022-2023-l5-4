package org.springframework.samples.dobble.card;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.dobble.symbol.Symbol;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardService {
	@Autowired
	private CardRepository mazoRepo;
	

	@Transactional(readOnly = true)
	public List<Card> findAll() {
		return (List<Card>) mazoRepo.findAll();
	}
	
	@Transactional
	public void deleteById(long id) {
		mazoRepo.deleteById(id);
	}

	@Transactional
	public void save(Card mazo) {
		mazoRepo.save(mazo);
	}

	@Transactional(readOnly = true)
	public Optional<Card> findById(long id) {
		return mazoRepo.findById(id);
	}

	@Transactional
	public void resetSymbols() {
		Iterable<Card> mazos=mazoRepo.findAll();
		List<Symbol> symbolsToBeRemoved=new ArrayList<>();
		for(Card mazo:mazos) {
			symbolsToBeRemoved.clear();
			for(Symbol symbol:mazo.getSymbols())
				symbolsToBeRemoved.add(symbol);
			mazo.getSymbols().removeAll(symbolsToBeRemoved);
			mazoRepo.save(mazo);
		}
		
	}
}
