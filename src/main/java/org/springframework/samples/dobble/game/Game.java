package org.springframework.samples.dobble.game;

import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.samples.dobble.card.Card;
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
@Table(name = "games")
public class Game extends BaseEntity{
    
    public Game(){}

    @ManyToOne
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

    @ManyToMany(fetch= FetchType.LAZY) 
    @JoinTable(
        name = "usergames", 
        joinColumns = @JoinColumn(name = "gameId", nullable = false, table = "games"),
        inverseJoinColumns = @JoinColumn(name = "userId", nullable = false, table = "users")
    )
    @Size(max = 6)
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "gamecards")
    private List<Card> centralDeck;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'LOBBY'")
    private GameState state;

    public Integer getNumUsers(){
        return this.users.size();
    }

    private Set<User> getUsersInternal(){
        if (this.getUsers() == null) setUsers(new HashSet<>());
        return this.getUsers();
    }
    public void addUser(User user) {
        this.getUsersInternal().add(user);
    }

    public void removeUser(User user){
        this.getUsersInternal().remove(user);
    }

    public boolean isFinished() {
        return this.state==GameState.FINISHED;
    }

    public boolean hasStarted() {
        return this.state!=GameState.LOBBY;
    }

}
