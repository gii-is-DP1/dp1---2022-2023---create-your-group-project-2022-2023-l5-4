package org.springframework.samples.dobble.card;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.symbol.Symbol;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cards")
public class Card extends BaseEntity{

    @Size(min=8,max=8)
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="cardsymbols",
    joinColumns = @JoinColumn(name="card_id"),
    inverseJoinColumns = @JoinColumn(name="symbol_id"))
	private List<Symbol> symbols;
    
}

