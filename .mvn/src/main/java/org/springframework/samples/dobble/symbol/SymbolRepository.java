
package org.springframework.samples.dobble.symbol;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends CrudRepository<Symbol,Long> {



}
