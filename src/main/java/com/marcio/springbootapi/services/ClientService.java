package com.marcio.springbootapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.dtos.ClientDto;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.services.exceptions.DataIntegrityException;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;

	public List<Client> getClients() {
		return repo.findAll();
	}

	public Client getById(Integer id) {
		Optional<Client> client = repo.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Client not found! id: " + Client.class.getName()));
	}

	public Client update(Client obj) {
		Client newClient = getById(obj.getId());
		updateData(newClient,obj);
		return repo.save(newClient);
	}

	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
		
	}

	public void delete(Integer id) {
		getById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cant delete a Clinet that have relations!");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDto(ClientDto obj) {
		return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null);
	}

}
