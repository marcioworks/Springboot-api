package com.marcio.springbootapi.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryResource {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Category> list() {
		
		Category cat1 = new Category(1, "Eletronics");
		Category cat2 = new Category(2, "Sports");
		
		List<Category> categories = new ArrayList<>();
		categories.addAll(Arrays.asList(cat1,cat2));
		return categories;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable Integer id){
		Category category = categoryService.getById(id);
		
		return ResponseEntity.ok().body(category);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Category obj){
		 obj = categoryService.insert(obj);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		 return ResponseEntity.created(uri).build();
	}
}
