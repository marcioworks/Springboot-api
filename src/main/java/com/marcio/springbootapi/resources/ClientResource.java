package com.marcio.springbootapi.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.dtos.ClientDto;
import com.marcio.springbootapi.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientResource {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<List<ClientDto>> list() {

		List<Client> categories = clientService.getClients();
		List<ClientDto> list = categories.stream().map(x -> new ClientDto(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Integer id) {
		Client client = clientService.getById(id);

		return ResponseEntity.ok().body(client);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDto objDto, @PathVariable Integer id) {
		Client obj = clientService.fromDto(objDto);
		obj.setId(id);
		obj = clientService.update(obj);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClientDto>> findPerPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Client> categories = clientService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClientDto> list = categories.map(x -> new ClientDto(x));

		return ResponseEntity.ok().body(list);
	}

}
