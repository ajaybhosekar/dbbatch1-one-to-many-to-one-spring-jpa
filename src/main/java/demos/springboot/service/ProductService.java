package demos.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import demos.springboot.dao.ProductDAO;
import demos.springboot.exceptions.NullObjectException;
import demos.springboot.exceptions.ResourceNotFoundException;
import demos.springboot.model.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO dao;
	
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		Iterable<Product> it = dao.findAll();
		it.forEach(product -> {
			products.add(product);
		});
		return products; 
	}
	
	public Product getProductById(int id) {
		Optional<Product> opt = dao.findById(id);
		Product prod = null;
		if(opt.isPresent()) {
			prod = opt.get();
		}
		return prod;
	}
	
	public Product addProduct(Product product) {
		
		if(product == null) {
			throw new NullObjectException("Product object is null in addProduct method");
		}
		return dao.save(product);
	}
	
	public Product updateProduct(Product product) {
		if(product == null) {
			throw new NullObjectException("Product object is null in addProduct method");
		}
		Optional<Product> opt = dao.findById(product.getProdId());
		Product prod = null;
		if(opt.isPresent()) {
			prod = dao.save(product);
		}
		else {
			throw new ResourceNotFoundException("No product with id: "+product.getProdId()+" found");
		}
		return prod;
	}
	
	public void delete(int prodId) {
		
		Optional<Product> opt = dao.findById(prodId);
		Product prod = null;
		if(opt.isPresent()) {
			prod = opt.get();
			dao.delete(prod);
		}
		else {
			throw new ResourceNotFoundException("No product with id: "+prodId+" found");
		}
	}
	
	public List<Product> findAllByCategoryCategoryId(int catId) {
		return dao.findAllByCategoryCategoryId(catId);
	}

}
