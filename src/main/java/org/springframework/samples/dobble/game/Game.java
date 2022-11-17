package org.springframework.samples.dobble.game;

import javax.enterprise.inject.Default;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.user.User;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany(mappedBy = "games")
    @Size(max = 6)
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "gamecards")
    private List<Card> centralDeck;

    public Integer getNumUsers(){
        return this.users.size();
    }

    public void addUser(User user) {
        this.users.add(user);
    }

}
