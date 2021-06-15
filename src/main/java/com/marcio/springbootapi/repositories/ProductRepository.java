package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
