package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
