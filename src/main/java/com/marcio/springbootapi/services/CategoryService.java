package com.marcio.springbootapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.dtos.CategoryDto;
import com.marcio.springbootapi.repositories.CategoryRepository;
import com.marcio.springbootapi.services.exceptions.DataIntegrityException;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public List<Category> getCategories(){
		return repo.findAll();
	}
	
	public Category getById(Integer id) {
		Optional<Category> category = repo.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Category not Found! id: "+ Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Category update(Category obj) {
		getById(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		getById(id);
		try {
			repo.deleteById(id);
		}catch( DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cant delete a category that have products!");
		}
	}
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category fromDto(CategoryDto obj) {
		return new Category(obj.getId(),obj.getName());
	}
}
