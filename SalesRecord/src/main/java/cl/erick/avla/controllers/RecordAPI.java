package cl.erick.avla.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.erick.avla.models.Product;
import cl.erick.avla.models.Record;
import cl.erick.avla.models.User;
import cl.erick.avla.services.AppService;

@RestController
@RequestMapping("/api/record")
@CrossOrigin("*")
public class RecordAPI {
	
	@Autowired
	private AppService appService;
	
	// CREATE NEW RECORD
	@PostMapping("/new")
	public ResponseEntity<Record> newRecord(@RequestParam("idUser") Long idUser, @RequestParam("idProducto") Long idProducto, @RequestParam("movimiento") String movimiento) {
		Record historial = new Record();
		try {
			historial.setUser(appService.findUserById(idUser));
			historial.setProducto(appService.findProductById(idProducto));
			historial.setMovimiento(movimiento);
			System.out.println(historial.getUser());
			if(historial.getUser() != null  && historial.getProducto() != null && historial.getMovimiento() != "") {
				appService.createRecord(historial);
				return new ResponseEntity<Record>(historial, HttpStatus.OK);
			}
			return new ResponseEntity<Record>(historial, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			return new ResponseEntity<Record>(historial, HttpStatus.BAD_REQUEST);
		}
	}
		
	// GET ALL RECORDS
	@GetMapping("/all")
	public List<Record> getAllRecords(){
		return appService.findAllRecords();
	}
		
	// GET RECORD BY USER
	@GetMapping("/user/{id}")
	public List<Record> getRecordByUser(@PathVariable Long id){
		User user = appService.findUserById(id);
		return appService.findRecordsByUserId(user);
	}
	
	// GET RECORD BY USER
		@GetMapping("/product/{id}")
		public List<Record> getRecordByProduct(@PathVariable Long id){
			Product product = appService.findProductById(id);
			return appService.findRecordsByProductId(product);
		}
		
		

}
