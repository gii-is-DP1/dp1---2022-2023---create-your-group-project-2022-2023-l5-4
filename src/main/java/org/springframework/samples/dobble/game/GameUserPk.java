package org.springframework.samples.dobble.game;

import java.io.Serializable;


import org.springframework.samples.dobble.user.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class GameUserPk implements Serializable{
    private String user;
    private Long game;

    public GameUserPk(){}

    public GameUserPk(User user, Game game) {
        this.user = user.getUsername();
        this.game = game.getId();
    }

    public static GameUserPk of (User user, Game game){
        return new GameUserPk(user,game);
    }
}
