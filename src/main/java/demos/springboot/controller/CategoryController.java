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
import demos.springboot.service.CategoryService;
import demos.springboot.service.ProductService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping("/api")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	//@RequestMapping(value ="/products", method = RequestMethod.GET) // http://localhost:9090/api/categories
	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return service.getAllCategories();
	}
	
	//@RequestMapping(value ="/products", method = RequestMethod.POST)
	@PostMapping("/categories")
	public Category addCategory(@RequestBody Category c) {
		return service.addCategory(c);
	}
	
	//@RequestMapping(value = "/products/{id}", method = RequestMethod.GET) //http://localhost:9090/api/categories/2
	@GetMapping("/categories/{id}")
	public Category getCategoryById(@PathVariable("id")int id) { 
		return service.getCategoryById(id);
		
	}
	
	
	//@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT) // http://localhost:9090/api/categories/1
	@PutMapping("/categories/{id}")
	public Category updateCategory(@PathVariable("id") int catId, @RequestBody Category category) { 
		category.setCategoryId(catId);
		return service.updateCategory(category);	
	}
	
	//@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE) //http://localhost:9090/api/categories/1
	@DeleteMapping("/categories/{id}")
	public boolean delete(@PathVariable("id")int catId) { 
		service.delete(catId);
		return true;
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
