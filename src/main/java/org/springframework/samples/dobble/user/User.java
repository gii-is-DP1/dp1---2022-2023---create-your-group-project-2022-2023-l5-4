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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.samples.dobble.comment.Comment;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.statistics.Achievement;
import org.springframework.samples.dobble.tournament.Tournament;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Audited
@Table(name = "users")
public class User {

	public User(){}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@NotAudited
    private List<User> friends;
	
	@Id
	String username;
	
	String password;

	@NotNull
	@Size(min=6, max=255)
	@Email
	private String email;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@NotAudited
	private Set<Authorities> authorities;

	@ManyToMany
	@NotAudited
	@JoinTable(name = "user_achievement",
			   joinColumns = @JoinColumn(name = "username"),
			   inverseJoinColumns = @JoinColumn(name = "achievement_id"))
	private Set<Achievement> achievements;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotAudited
	private Game currentGame;

	@ManyToOne(cascade = CascadeType.ALL)
	@NotAudited
	private Tournament currentTournament;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	@NotAudited
	private List<Game> ownedGames;

	@OneToMany(mappedBy = "winner", cascade = CascadeType.ALL)
	@NotAudited
	private List<Game> wonGames;

	@OneToMany(cascade = CascadeType.ALL)
	@NotAudited
	private List<Comment> comments;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@NotAudited
	private List<Tournament> tournaments;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	@NotAudited
	private List<Tournament> ownedTournament;

	@OneToMany(mappedBy = "winner",cascade = CascadeType.ALL)
	@NotAudited
	private List<Tournament> wonTournamnets;

	@ColumnDefault("true")
	private Boolean enabled;

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

	public boolean isNew() {
		return this.username == null;
	}
}
