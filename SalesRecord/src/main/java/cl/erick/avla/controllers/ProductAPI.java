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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.erick.avla.models.Product;
import cl.erick.avla.services.AppService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductAPI {
	
	@Autowired
	private AppService appService;
	
	// CREATE NEW PRODUCT
	@PostMapping("/new")
	public ResponseEntity<Product> newProduct(@RequestParam("name") String name, @RequestParam("cantidad") Integer cantidad) {
		Product product = new Product();
		product.setCantidad(cantidad);
		product.setNombre(name);
		appService.createProduct(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	// GET ALL PRODUCT
	@GetMapping("/all")
	public List<Product> getAllProducts(){
		return appService.findAllProducts();
	}
		
	// GET PRODUCT BY ID
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id){
		return appService.findProductById(id);
	}
	
	// DELETE PRODUCT
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		Product product = appService.findProductById(id);
		if (product != null) {
			appService.deleteProduct(id);
		}
		else {
			return new ResponseEntity<String>("Delete Product Error", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Delete Product Ok!", HttpStatus.OK);
	}
		
	// UPDATE PRODUCT
	@PutMapping("/edit/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, 
			@RequestParam(value = "nombre", required = false) String nombre, 
			@RequestParam(value = "estado", required = false) String estado,
			@RequestParam(value = "cantidad", required = false) Integer cantidad){
		Product product = appService.findProductById(id);
		System.out.println(nombre);
		System.out.println(estado);
		System.out.println(cantidad);
		if (product != null && cantidad != null || nombre != null || estado != null ) {
			if(nombre != null) {
				System.out.println(1);
				product.setNombre(nombre);
			}
			if(estado != null) {
				System.out.println(2);
				product.setEstado(estado);
			}
			if(cantidad !=null) {
				System.out.println(3);
				product.setCantidad(cantidad);
			}
			appService.createProduct(product);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
		
		
	}

}
