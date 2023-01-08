package org.springframework.samples.dobble.statistics;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer>{
    List<Achievement> findAll();

    @Query("SELECT u.achievements FROM User u WHERE u.id=:userid")
    public List<Achievement> findPlayerAchievements(@Param("userid") String userid);

    public Achievement findByName(String name);
}
