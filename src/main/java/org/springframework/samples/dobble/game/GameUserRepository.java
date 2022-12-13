package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.dobble.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUserRepository extends CrudRepository<GameUser,GameUserPk> {

    @Query("SELECT gameuser FROM GameUser gameuser")
    List<GameUser> findAll() throws DataAccessException;

}