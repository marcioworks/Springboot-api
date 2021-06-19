package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.Pedido;

public interface OrderRepository extends JpaRepository<Pedido, Integer> {

}
