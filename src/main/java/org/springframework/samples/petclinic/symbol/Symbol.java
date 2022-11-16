

package org.springframework.samples.petclinic.symbol;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.samples.petclinic.card.Card;

import lombok.Getter;
import lombok.Setter;

@Entity	
@Getter
@Setter
public class Symbol {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	private long id;
	
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@ManyToMany(targetEntity=Card.class,fetch=FetchType.EAGER,mappedBy = "symbols")
	private List<Card> Cards;	
}
