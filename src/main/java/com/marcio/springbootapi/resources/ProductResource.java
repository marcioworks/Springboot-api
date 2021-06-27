package com.marcio.springbootapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.marcio.springbootapi.domain.Product;
import com.marcio.springbootapi.dtos.ProductDto;
import com.marcio.springbootapi.resources.utils.URL;
import com.marcio.springbootapi.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductResource {

	@Autowired
	private ProductService ProductService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findById(@PathVariable Integer id) {
		Product product = ProductService.getById(id);

		return ResponseEntity.ok().body(product);
	}

	@GetMapping()
	public ResponseEntity<Page<ProductDto>> findPerPage(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeListInt(categories);
		Page<Product> products = ProductService.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDto> list = products.map(x -> new ProductDto(x));

		return ResponseEntity.ok().body(list);
	}
}
