package demos.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import demos.springboot.exceptions.ExceptionDetails;
import demos.springboot.exceptions.NullObjectException;
import demos.springboot.exceptions.ResourceNotFoundException;
import demos.springboot.model.Category;
import demos.springboot.model.Product;
import demos.springboot.service.ProductService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	//@RequestMapping(value ="/products", method = RequestMethod.GET) // http://localhost:9090/api/products
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return service.getAllProducts();
	}
	
	@GetMapping("/categories/{catId}/products")
	public List<Product> findAllByCategoryCategoryId(@PathVariable("catId")int catId) { //http://localhost:9090/api/categories/{catId}/products
		return service.findAllByCategoryCategoryId(catId);
	}
	
	//@RequestMapping(value ="/categories/{catId}/products", method = RequestMethod.POST) // http://localhost:9090/api/categories/{catId}/products
	@PostMapping("/categories/{catId}/products")
	public Product addProduct(@PathVariable("catId") int catId, @RequestBody Product p) {
		p.setCategory(new Category(catId,"",""));
		return service.addProduct(p);
	}
	
	//@RequestMapping(value = "/products/{id}", method = RequestMethod.GET) //http://localhost:9090/api/products/2
	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable("id")int id) { 
		return service.getProductById(id);
		
	}
	
	
	//@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT) // http://localhost:9090/api/products/1
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable("id") int prodId, @RequestBody Product product) { 
		product.setProdId(prodId);
		return service.updateProduct(product);	
	}
	
	//@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE) //http://localhost:9090/api/products/1
	@DeleteMapping("/products/{id}")
	public void delete(@PathVariable("id")int prodId) { 
		service.delete(prodId);
	}
	
	
	
	
	
	
	/*
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ResourceNotFoundException handleResourceNotFoundException(ResourceNotFoundException e) {
		return e;
	}*/
	
	
	
	/*
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public String handleResourceNotFoundException(ResourceNotFoundException e) {
		return e.getMessage();
	}
	*/
	
	/*
	@ExceptionHandler(value = {ResourceNotFoundException.class})
	public ExceptionDetails handleResourceNotFoundException(ResourceNotFoundException e, WebRequest req) {
		ExceptionDetails ed = new ExceptionDetails();
		ed.setMessage(e.getMessage());
		ed.setUrl(req.getDescription(false));
		return ed;
	}
	
	@ExceptionHandler(value = {NullObjectException.class})
	public ExceptionDetails handleNullObjectException(NullObjectException e, WebRequest req) {
		ExceptionDetails ed = new ExceptionDetails();
		ed.setMessage(e.getMessage());
		ed.setUrl(req.getDescription(false));
		return ed;
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ExceptionDetails handleGenericException(Exception e, WebRequest req) {
		ExceptionDetails ed = new ExceptionDetails();
		ed.setMessage(e.getMessage());
		ed.setUrl(req.getDescription(false));
		return ed;
	}
	*/
	
	
}
