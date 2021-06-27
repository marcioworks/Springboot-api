package com.marcio.springbootapi.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.domain.Pedido;
import com.marcio.springbootapi.domain.Product;
import com.marcio.springbootapi.repositories.CategoryRepository;
import com.marcio.springbootapi.repositories.ProductRepository;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Product getById(Integer id) {
		Optional<Product> product = repo.findById(id);
		return product.orElseThrow(() -> new ObjectNotFoundException("Product not Found! id: "+ Pedido.class.getName()));
	}

	public Page<Product> search(String name, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepo.findAllById(ids);
		
		return repo.search(name,categories, pageRequest);

	}
	
}
