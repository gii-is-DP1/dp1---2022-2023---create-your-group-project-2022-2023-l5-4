package org.springframework.samples.dobble.user;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.tournament.Tournament;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{

	public User(){}
	
	@Id
	String username;
	
	String password;
	
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

	
    @ManyToMany(mappedBy = "users")
	private List<Game> games;

	@ManyToOne
	private Game currentGame;
	
	@OneToMany(mappedBy = "owner")
	private List<Game> ownedGames;

	@OneToMany(mappedBy = "winner")
	private List<Game> wonGames;

	
    @ManyToMany(mappedBy = "users")
	private List<Tournament> tournaments;

	@ManyToOne
	private Tournament currentTournament;
	
	@OneToMany(mappedBy = "owner")
	private List<Tournament> ownedTournament;

	@OneToMany(mappedBy = "winner")
	private List<Tournament> wonTournamnets;

	public String toString(){
		return this.username;
	}
}
