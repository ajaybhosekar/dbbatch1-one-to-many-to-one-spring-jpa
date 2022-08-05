package demos.springboot.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import demos.springboot.dao.CategoryDAO;
import demos.springboot.dao.ProductDAO;
import demos.springboot.exceptions.NullObjectException;
import demos.springboot.exceptions.ResourceNotEmptyException;
import demos.springboot.exceptions.ResourceNotFoundException;
import demos.springboot.model.Category;
import demos.springboot.model.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDAO dao;
	
	
	public List<Category> getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		Iterable<Category> it = dao.findAll();
		it.forEach(category -> {
			categories.add(category);
		});
		return categories; 
	}
	
	public Category getCategoryById(int id) {
		Optional<Category> opt = dao.findById(id);
		Category category = null;
		if(opt.isPresent()) {
			category = opt.get();
		}
		return category;
	}
	
	public Category addCategory(Category category) {
		
		if(category == null) {
			throw new NullObjectException("Category object is null in addCategory method");
		}
		return dao.save(category);
	}
	
	public Category updateCategory(Category category) {
		if(category == null) {
			throw new NullObjectException("Category object is null in updateCategory method");
		}
		Optional<Category> opt = dao.findById(category.getCategoryId());
		Category cat = null;
		if(opt.isPresent()) {
			cat = dao.save(category);
		}
		else {
			throw new ResourceNotFoundException("No category with id: "+category.getCategoryId()+" found");
		}
		return cat;
	}
	
	public void delete(int categoryId) {
		
		Optional<Category> opt = dao.findById(categoryId);
		Category cat = null;
		if(opt.isPresent()) {
			cat = opt.get();
			if(cat.getProducts().size() > 0) {
				throw new ResourceNotEmptyException("Category "+categoryId+" is contains some products hence cannot be deleted");
			}
			else {
				dao.delete(cat);
			}
			
		}
		else {
			throw new ResourceNotFoundException("No category with id: "+categoryId+" found");
		}
	}
	
	

}
