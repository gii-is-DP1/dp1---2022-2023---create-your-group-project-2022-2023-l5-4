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
    private User user;
    private Game game;

    public GameUserPk(){}

    public GameUserPk(User user, Game game) {
        this.user = user;
        this.game = game;
    }
}
