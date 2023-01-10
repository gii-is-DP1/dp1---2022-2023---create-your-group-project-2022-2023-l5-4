package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUserRepository extends CrudRepository<GameUser,GameUserPk> {

    public List<GameUser> findAll();

}