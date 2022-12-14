
package org.springframework.samples.dobble.symbol;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SymbolService {
	@Autowired
	private SymbolRepository symbolRepo;
	

	@Transactional(readOnly = true)
	public Iterable<Symbol> findAll() {
		return symbolRepo.findAll();
	}

	@Transactional
	public void save(Symbol symbol) {
		symbolRepo.save(symbol);
	}

	@Transactional(readOnly = true)
	public Optional<Symbol> findById(long id) {
		return symbolRepo.findById(id);
	}
	
}
