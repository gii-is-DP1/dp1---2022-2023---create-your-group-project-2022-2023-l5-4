package org.springframework.samples.dobble.tournament;


import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.game.Game;
import org.springframework.samples.dobble.game.GameMode;
import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.user.User;

import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = "tournaments")
public class Tournament extends BaseEntity {

    public Tournament() {
    }

    @ElementCollection
    @Column(name="gamemode")
    @Enumerated(EnumType.STRING)
	private List<GameMode> gamemodes;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ownerId")
    @NotAudited
    @NotNull
    private User owner;

    @ManyToOne
    @JoinColumn(name = "winnerId")
    @NotAudited
    private User winner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "usertournaments", joinColumns = @JoinColumn(name = "tournamentId", nullable = false, table = "tournaments"), inverseJoinColumns = @JoinColumn(name = "userId", nullable = false, table = "users"))
    @Size(max = 6)
    @NotAudited
    private List<User> users;

    @Size(max=8)
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @NotAudited
    @JoinTable(name = "gametournament", joinColumns = @JoinColumn(name = "tournamentId", nullable = false, table = "tournaments"), inverseJoinColumns = @JoinColumn(name = "gameId", nullable = false, table = "games"))
	private List<Game> games;

    @ManyToMany
    @NotAudited
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
    private String accessCode;

    public Integer getAccessCode() {
        return null;
    }

    public Boolean isPrivate() {
        return this.accessCode != null;
    }

    private Integer hashCode(String accessCode) {
        return accessCode.toString().hashCode();
    }

    public Boolean validAccessCode(String accessCode) {
        return this.accessCode == null || this.accessCode.equals(accessCode);
    }

    public void setAccessCode(String accessCode) {
        if (accessCode == "") this.accessCode = null;
        else this.accessCode = accessCode;
    }

    public Integer getNumUsers() {
        return this.users.size();
    }

    private List<User> getUsersInternal() {
        if (this.getUsers() == null)
            setUsers(new ArrayList<>());
        return this.getUsers();
    }

    public void addUser(User user) {
        this.getUsersInternal().add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
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
