package org.springframework.samples.dobble.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;



public interface UserRepository extends  PagingAndSortingRepository<User, String>{
    
    @Query("SELECT user FROM User user")
    List<User> findAll();

    @Query("SELECT u.username FROM User u")
    List<String> findAllUsernames();


    @Query("SELECT user FROM User user WHERE user.id=:username") 
	Optional<User> findUserByUsername(@Param("username") String username);


}
