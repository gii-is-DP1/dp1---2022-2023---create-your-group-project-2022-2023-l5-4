
package org.springframework.samples.dobble.symbol;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SymbolService {
	private SymbolRepository symbolRepository;
	private SymbolVariantRepository symbolVariantRepository;
	@Autowired
	public SymbolService(SymbolRepository symbolRepository, SymbolVariantRepository symbolVariantRepository){
		this.symbolRepository = symbolRepository;
		this.symbolVariantRepository = symbolVariantRepository;
	}


	@Transactional(readOnly = true)
	public Iterable<Symbol> findAll() {
		return symbolRepository.findAll();
	}

	@Transactional
	public void save(Symbol symbol) {
		symbolRepository.save(symbol);
	}

	@Transactional(readOnly = true)
	public Optional<Symbol> findById(long id) {
		return symbolRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public SymbolVariant findSymbolVariant(long symbolSetId, long symboId) {
		return symbolVariantRepository.findBySetAndSymbol(symbolSetId,symboId);
	}
	
}
