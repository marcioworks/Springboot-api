package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
