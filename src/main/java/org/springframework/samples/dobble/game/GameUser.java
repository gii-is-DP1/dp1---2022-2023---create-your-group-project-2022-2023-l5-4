
package org.springframework.samples.dobble.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.samples.dobble.card.HandedEntity;
import org.springframework.samples.dobble.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="gameusers")
@IdClass(GameUserPk.class)
public class GameUser extends HandedEntity {
    
    public GameUser(){
        this.score = 0;
    }

    
    public GameUser(User user, Game game) {
        this.game = game;
        this.user= user;
        this.score = 0;
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

    @ColumnDefault("0")
    private Integer score;

    public void addPoints(Integer points) {
        score+=points;
    }

    public void substractPoints(Integer points) {
        score-=points;
    }

    public String toString(){
        return user.toString();
    }

    public boolean equals(Object o) {
        if (o.getClass()!=this.getClass()) return false;
        GameUser other = (GameUser) o;
        return this.user.equals(other.getUser()) && this.game.equals(other.getGame());
    }

    public static List<User> userListOf(List<GameUser> gameUsers) {
        List<User> users = new ArrayList<>();
        for (GameUser gameUser : gameUsers) {
            users.add(gameUser.getUser());
        }
        return users;
    }

}


