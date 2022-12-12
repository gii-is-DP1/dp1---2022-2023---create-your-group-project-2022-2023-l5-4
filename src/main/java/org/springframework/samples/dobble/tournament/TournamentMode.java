package org.springframework.samples.dobble.tournament;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity	
@Getter
@Setter
@Table(name="tournamentmodes")
public class TournamentMode {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false, precision=10)
	private long id;
	
	
	@Column(nullable=false)
	private String name;
	
	@ManyToMany(targetEntity=Tournament.class,fetch=FetchType.EAGER,mappedBy = "tournamentmodes")
	private List<Tournament> Tournaments;	
}
