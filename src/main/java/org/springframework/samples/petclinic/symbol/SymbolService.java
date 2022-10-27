
package org.springframework.samples.petclinic.symbol;


import java.util.ArrayList;
import java.util.List;
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
	public void deleteById(long id) {
		symbolRepo.deleteById(id);
	}

	@Transactional
	public void save(Symbol symbol) {
		symbolRepo.save(symbol);
	}

	@Transactional(readOnly = true)
	public Optional<Symbol> findById(long id) {
		return symbolRepo.findById(id);
	}

	@Transactional(readOnly = true)
	public Symbol findByNombre(String nombre) {
		return symbolRepo.findByName(nombre);
	}
}
