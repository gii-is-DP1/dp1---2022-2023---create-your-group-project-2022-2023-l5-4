package org.springframework.samples.dobble.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.card.HandedEntity;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.statistics.Achievement;
import org.springframework.samples.dobble.tournament.Tournament;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends HandedEntity {

	public User(){}
	
	@ManyToMany(cascade = CascadeType.ALL)
    private List<User> friends;
	
	@Id
	String username;
	
	String password;

	@NotNull
	@Size(min=6, max=255)
	@Email
	private String email;
	
	boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;

	@ManyToMany
	@JoinTable(name = "user_achievement",
			   joinColumns = @JoinColumn(name = "username"),
			   inverseJoinColumns = @JoinColumn(name = "achievement_id"))
	private Set<Achievement> achievements;

	@ManyToOne(cascade = CascadeType.PERSIST)
	private Game currentGame;

	@ManyToOne
	private Tournament currentTournament;
	
	@OneToMany(mappedBy = "owner")
	private List<Game> ownedGames;

	@OneToMany(mappedBy = "winner")
	private List<Game> wonGames;

	
    @ManyToMany(mappedBy = "users")
	private List<Tournament> tournaments;
	
	@OneToMany(mappedBy = "owner")
	private List<Tournament> ownedTournament;

	@OneToMany(mappedBy = "winner")
	private List<Tournament> wonTournamnets;

	public String toString(){
		return this.username;
	}

    public void addFriend(User user) {
        if (friends == null)
            friends = new ArrayList<>();
        friends.add(user);
    }

    public void removeFriend(User user) {
        friends.remove(user);
    }

	public Boolean equals(User other) {
		return this.username.equals(other.getUsername());
	}

}
