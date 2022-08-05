package demos.springboot.dao;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import demos.springboot.model.Product;

public interface ProductDAO extends CrudRepository<Product, Integer>{
	
	public List<Product> findAllByCategoryCategoryId(int catId);
	
}
