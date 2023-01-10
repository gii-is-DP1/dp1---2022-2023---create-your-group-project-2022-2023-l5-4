package org.springframework.samples.dobble.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface UserRepository extends  PagingAndSortingRepository<User, String>{
    
    @Query("SELECT user FROM User user")
    List<User> findAll();


	
}
