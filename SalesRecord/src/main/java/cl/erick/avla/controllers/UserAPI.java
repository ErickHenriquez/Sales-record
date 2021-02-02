package cl.erick.avla.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.erick.avla.models.User;
import cl.erick.avla.services.AppService;



@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserAPI {
	
	@Autowired
	private AppService appService;
	
	// CREATE NEW USER
	@PostMapping("/registration")
	public ResponseEntity<User> registerUser(@RequestBody User usuario) {
		User u = appService.findUserByEmail(usuario.getEmail());
		if ( u != null) {
			return new ResponseEntity<User>(usuario, HttpStatus.BAD_REQUEST);
		}
		else {
			User user = this.appService.registerUser(usuario);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
	}

	// LOGIN USER
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
		User user = appService.findUserByEmail(email);
		if (this.appService.authenticateUser(email, password)) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
	}
	
	// GET ALL USER
	@GetMapping("/all")
	public List<User> getUsuarios(){
		return appService.findAllUsers();
	}
	
	// GET USER BY ID
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return appService.findUserById(id);
	}
	
	// DELETE USER
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id){
		User user = appService.findUserById(id);
		if (user != null) {
			appService.deleteUser(id);
		}
		else {
			return new ResponseEntity<String>("Delete User Error", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Delete User Ok!", HttpStatus.OK);
	}
	
	// UPDATE USER
	@PutMapping("/edit/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestParam(value = "name", required = false) String name){
		User user = appService.findUserById(id);
		if (user != null) {
			user.setName(name);
			appService.updateUser(user);
		}
		else{
			return new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
