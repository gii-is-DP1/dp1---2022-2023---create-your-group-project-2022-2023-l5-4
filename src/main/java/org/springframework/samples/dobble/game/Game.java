package org.springframework.samples.dobble.game;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.card.HandedEntity;
import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.tournament.Tournament;
import org.springframework.samples.dobble.user.User;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends HandedEntity {

    public Game() {
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "gamemodeId")
    @NotNull
    private GameMode gamemode;

    @ManyToOne
    @JoinColumn(name = "ownerId")
    @NotNull
    private User owner;

    @ManyToOne
    @JoinColumn(name = "winnerId")
    private User winner;

    @ManyToMany
    @Size(min = 1, max = 6)
    @JoinTable(name = "gameusers", 
    joinColumns = @JoinColumn(name="gameId"), 
    inverseJoinColumns = @JoinColumn(name="userId"))
    private List<User> users;

    @ManyToMany(targetEntity=Tournament.class,fetch=FetchType.LAZY,mappedBy = "games",cascade = CascadeType.ALL)
	private List<Tournament> Tournaments;	

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'LOBBY'")
    private GameState state;

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

    public boolean isFinished() {
        return this.state == GameState.FINISHED;
    }

    public boolean hasStarted() {
        return this.state != GameState.LOBBY;
    }

    public boolean isOnPlay() {
        return this.state == GameState.ON_PLAY;
    }
    public boolean isFull() {
        return this.getUsers().size()==this.maxPlayers;
    }

}
