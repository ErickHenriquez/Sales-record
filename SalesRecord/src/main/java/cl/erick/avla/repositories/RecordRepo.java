package cl.erick.avla.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.erick.avla.models.Record;

@Repository
public interface RecordRepo extends CrudRepository<Record, Long> {
	
	List<Record> findAll();

}
