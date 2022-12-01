package org.springframework.samples.dobble.card;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.symbol.Symbol;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="cards")
public class Card {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
    private long id;

    private String name;

    @Size(max=8)
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "symbolcard", joinColumns = @JoinColumn(name = "cardId", nullable = false, table = "cards"), inverseJoinColumns = @JoinColumn(name = "symbolId", nullable = false, table = "symbols"))
	private List<Symbol> symbols;
    
}

