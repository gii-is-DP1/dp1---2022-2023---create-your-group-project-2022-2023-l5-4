package org.springframework.samples.dobble.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
    List<User> findAll();

	
}
