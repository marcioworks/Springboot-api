package com.marcio.springbootapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marcio.springbootapi.domain.Pedido;
import com.marcio.springbootapi.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderResource {
	
	@Autowired
	private OrderService orderService;

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> findById(@PathVariable Integer id){
		Pedido pedido = orderService.getById(id);
		
		return ResponseEntity.ok().body(pedido);
	}
}
