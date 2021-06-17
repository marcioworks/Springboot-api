package com.marcio.springbootapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.services.ClientService;
@RestController
@RequestMapping("/client")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Integer id){
		Client client = clientService.getById(id);
		
		return ResponseEntity.ok().body(client);
	}
	
}
