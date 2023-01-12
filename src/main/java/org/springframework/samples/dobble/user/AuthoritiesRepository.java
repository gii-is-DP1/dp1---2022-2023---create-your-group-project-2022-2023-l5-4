package org.springframework.samples.dobble.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long>{

    @Query("SELECT auth FROM Authorities auth WHERE user=:user AND authority=:role")
    Authorities findByUserAndRole(@Param("user") User user, @Param("role") String role);
	
}
 