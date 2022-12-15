package org.springframework.samples.dobble.symbol;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolVariantRepository extends CrudRepository<Symbol,Long> {

    @Query("SELECT symbolVariant FROM SymbolVariant symbolVariant WHERE symbolVariant.symbolSet.id=?#{#symbolSetId} AND symbolVariant.symbol.id=?#{#symbolId}")
    SymbolVariant findBySetAndSymbol(@Param("symbolSetId") long symbolSetId, @Param("symbolId") long symboId);



}