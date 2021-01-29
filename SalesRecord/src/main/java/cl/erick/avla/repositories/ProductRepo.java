package cl.erick.avla.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.erick.avla.models.Product;

@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
	
	List<Product> findAll();

}
