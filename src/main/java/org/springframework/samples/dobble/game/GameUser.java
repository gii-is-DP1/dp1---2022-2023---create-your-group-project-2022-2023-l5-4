package org.springframework.samples.dobble.game;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.samples.dobble.card.Card;
import org.springframework.samples.dobble.model.BaseEntity;
import org.springframework.samples.dobble.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="gameusers")
@IdClass(GameUserPk.class)
public class GameUser {
    
    public GameUser(){}

    
    public GameUser(User user, Game game) {
        this.game=game;
        this.user=user;
    }

    @Id
    @ManyToOne
    @JoinColumn(name="game_id")
    @NotNull
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    private User user;
    
    @ManyToMany
    List<Card> cards;
    
    public String toString(){
        return user.toString();
    }

    public boolean equals(Object o) {
        if (o.getClass()!=this.getClass()) return false;
        GameUser other = (GameUser) o;
        return this.user.equals(other.getUser()) && this.game.equals(other.getGame());
    }


}

