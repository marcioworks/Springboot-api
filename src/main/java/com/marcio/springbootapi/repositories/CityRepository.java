package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.City;

public interface CityRepository extends JpaRepository<City, Integer>{

}
