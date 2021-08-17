package com.marcio.springbootapi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.dtos.CategoryDto;
import com.marcio.springbootapi.services.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@RequestMapping("/category")
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value="busca por id")
	@GetMapping
	public ResponseEntity<List<CategoryDto>> list() {

		List<Category> categories = categoryService.getCategories();
		List<CategoryDto> list = categories.stream().map(x -> new CategoryDto(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(list);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable Integer id) {
		Category category = categoryService.getById(id);

		return ResponseEntity.ok().body(category);
	}

	@PostMapping
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDto objDto) {
		Category obj = categoryService.fromDto(objDto);
		obj = categoryService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDto objDto, @PathVariable Integer id) {
		Category obj = categoryService.fromDto(objDto);
		obj.setId(id);
		obj = categoryService.update(obj);

		return ResponseEntity.noContent().build();
	}

	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Não é possível excluir uma categoria que possui produtos"),
			@ApiResponse(code = 404, message = "Código inexistente") })
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoryDto>> findPerPage(
			@RequestParam (value = "page", defaultValue = "0")Integer page, 
			@RequestParam (value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
			@RequestParam (value = "orderBy", defaultValue = "name")String orderBy,
			@RequestParam (value = "direction", defaultValue = "ASC")String direction){
		

		Page<Category> categories = categoryService.findPage(page,linesPerPage,orderBy,direction);
		Page<CategoryDto> list = categories.map(x -> new CategoryDto(x));

		
		return ResponseEntity.ok().body(list);
	}

}
