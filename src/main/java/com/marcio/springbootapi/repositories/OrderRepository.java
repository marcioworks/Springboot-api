package com.marcio.springbootapi.repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.domain.Pedido;

public interface OrderRepository extends JpaRepository<Pedido, Integer> {

	Page<Pedido> findByClient(Client client, Pageable pageRequest);


}
