package cl.erick.avla.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.erick.avla.models.Product;
import cl.erick.avla.models.Record;
import cl.erick.avla.models.User;


@Repository
public interface RecordRepo extends CrudRepository<Record, Long> {
	
	List<Record> findAll();
	
	List<Record> findByUser(User user);
	
	List<Record> findByProduct(Product product);

}
