package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
