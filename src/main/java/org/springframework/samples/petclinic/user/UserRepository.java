package org.springframework.samples.petclinic.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
    List<User> findAll();
    @Query("SELECT s FROM User s WHERE s.user.username=:username")
    User findUser();

	
}
