package org.springframework.samples.dobble.user;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.dobble.model.BaseEntity;




public interface UserRepository extends  CrudRepository<User, String>{
    
  
	/**
	 * Save an <code>User</code> to the data store, either inserting or updating it.
	 * @param user the <code>User</code> to save
	 * @return 
	 * @see BaseEntity#isNew
	 */
	@SuppressWarnings("unchecked")
	User save(User user) throws DataAccessException;

	@Query("SELECT user FROM User user")
    List<User> findAll();


	@Query("SELECT user FROM User user WHERE user.username =:username")
	Optional<User> findUsername(@Param("username") String username);
	
}
