package org.springframework.samples.dobble.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUserRepository extends CrudRepository<GameUser,GameUserPk> {

    public List<GameUser> findAll();

    @Query("SELECT SUM(gu.score) FROM GameUser gu WHERE gu.user.username = :username")
    Integer findTotalScoreByUsername(@Param("username") String username);

}