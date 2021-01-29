package cl.erick.avla.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.erick.avla.models.User;


@Repository
public interface UserRepo extends CrudRepository<User, Long>{
	
	List<User> findAll();
	
	User findByEmail(String email);

}
