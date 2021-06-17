package com.marcio.springbootapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	
	public Client getById(Integer id) {
		Optional<Client> client = repo.findById(id);
		return client.orElseThrow(() -> new  ObjectNotFoundException("Client not found! id: " + Client.class.getName() ));
	}
	
	
}
