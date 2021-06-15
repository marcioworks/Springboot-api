package com.marcio.springbootapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public Category getById(Integer id) {
		Optional<Category> category = repo.findById(id);
		return category.orElse(null);
	}
	
}
