package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.dobble.user.User;

public interface GameUserRepository extends CrudRepository<GameUser,GameUserPk> {

}