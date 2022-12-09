package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.dobble.user.User;

public interface GameUserRepository extends CrudRepository<GameUser,Long> {

    @Query("SELECT gameUser FROM GameUser gameUser WHERE gameUser.user=user AND gameUser.game=game")
    List<GameUser> findByGameAndUser(Game game, User user);
}