package com.marcio.springbootapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Pedido;
import com.marcio.springbootapi.repositories.OrderRepository;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	public Pedido getById(Integer id) {
		Optional<Pedido> pedido = repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("pedido not Found! id: "+ Pedido.class.getName()));
	}
	
}
