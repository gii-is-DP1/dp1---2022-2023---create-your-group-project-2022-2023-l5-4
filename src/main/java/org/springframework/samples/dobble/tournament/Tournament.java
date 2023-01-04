package org.springframework.samples.dobble.tournament;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.user.User;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tournaments")
public class Tournament extends BaseEntity {

    public Tournament() {
    }

    @ElementCollection(targetClass = GameMode.class)
    @Column(name="gamemode")
    @Enumerated(EnumType.STRING)
	private List<GameMode> gamemodes;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    @NotNull
    private User owner;

    @ManyToOne
    @JoinColumn(name = "winnerId")
    private User winner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usertournaments", joinColumns = @JoinColumn(name = "tournamentId", nullable = false, table = "tournaments"), inverseJoinColumns = @JoinColumn(name = "userId", nullable = false, table = "users"))
    @Size(max = 6)
    private Set<User> users;

    @Size(max=8)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "gametournament", joinColumns = @JoinColumn(name = "tournamentId", nullable = false, table = "tournaments"), inverseJoinColumns = @JoinColumn(name = "gameId", nullable = false, table = "games"))
	private List<Game> games;

    @ManyToMany
    @JoinTable(name = "tournamentcards")
    private List<Card> centralDeck;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'LOBBY'")
    private TournamentState state;

    @Min(2)

    @Max(6)
    @ColumnDefault("6")
    private Integer maxPlayers;

    @ColumnDefault("null")
    private Integer accessCode;

    public Integer getAccessCode() {
        return null;
    }

    public Boolean isPrivate() {
        System.out.println(this.accessCode != null);
        return this.accessCode != null;
    }

    private Integer hashCode(String accessCode) {
        return accessCode.toString().hashCode();
    }

    public void setAccessCode(String accessCode) {

        if (!(accessCode == null || accessCode == ""))
            this.accessCode = hashCode(accessCode);
    }

    public Boolean validAccessCode(String accessCode) {
        if (this.accessCode != null)
            return this.accessCode.equals(hashCode(accessCode));
        return true;
    }

    public Integer getNumUsers() {
        return this.users.size();
    }

    private Set<User> getUsersInternal() {
        if (this.getUsers() == null)
            setUsers(new HashSet<>());
        return this.getUsers();
    }

    public void addUser(User user) {
        this.getUsersInternal().add(user);
    }

    public void removeUser(User user) {
        this.getUsersInternal().remove(user);
    }

    public boolean isFinished() {
        return this.state == TournamentState.FINISHED;
    }

    public boolean hasStarted() {
        return this.state != TournamentState.LOBBY;
    }

    public boolean isFull() {
        return this.getUsers().size()==this.maxPlayers;
    }

}
