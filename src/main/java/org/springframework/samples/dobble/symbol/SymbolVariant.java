package org.springframework.samples.dobble.symbol;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity	
@Getter
@Setter
@Table(name="symbolvariants")
public class SymbolVariant extends NamedEntity {
	
	@NotNull
	private String fileName;

	@ManyToOne
	@NotNull
	private Symbol symbol;

	@ManyToOne
	@NotNull
	private SymbolSet symbolSet;

}
