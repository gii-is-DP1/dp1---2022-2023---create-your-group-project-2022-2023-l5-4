package org.springframework.samples.petclinic.card;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.symbol.Symbol;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Card {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
    private long id;

    @ManyToMany(fetch=FetchType.EAGER)
	private List<Symbol> symbols;
    
}
