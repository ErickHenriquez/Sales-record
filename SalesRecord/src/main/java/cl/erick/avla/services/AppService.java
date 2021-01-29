package cl.erick.avla.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.erick.avla.models.Product;
import cl.erick.avla.models.User;
import cl.erick.avla.models.Record;

import cl.erick.avla.repositories.ProductRepo;
import cl.erick.avla.repositories.RecordRepo;
import cl.erick.avla.repositories.UserRepo;

@Service
public class AppService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private RecordRepo recordRepo;
	
	// USER SERVICES
	
	// AUTHENTICATE USER
	public boolean authenticateUser(String email, String password) {
		// first find the user by email
		User user = userRepo.findByEmail(email);
		// if we can't find it by email, return false
		if (user == null) {
			return false;
		}
		// if the passwords match, return true, else, return false
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	// REGISTER USER AND HASH THEIR PASSWORD
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}
	
	// UPDATE USER WITHOUT PASSWORD
	public User updateUser(User user) {
		return userRepo.save(user);
	}
	
	// FIND ALL USERS
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
	
	// FIND USER BY EMAIL
	public User findUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	
	// FIND USER BY ID
	public User findUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if (user.isPresent()) {
			return user.get();
		} 
		else{
			return null;
		}
	}
	
	// DELETE USER
	public void deleteUser(Long id) {
		userRepo.delete(findUserById(id));
	}
	
	// PRODUCT SERVICES
	
	public List<Product> findAllProducts(){
		return productRepo.findAll();
	}
	
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	
	// RECORD SERVICES
	
	public List<Record> findAllRecords(){
		return recordRepo.findAll();
	}
	
	public Record createRecord(Record record) {
		return recordRepo.save(record);
	}

}
