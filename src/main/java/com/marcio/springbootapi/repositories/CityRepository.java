package com.marcio.springbootapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.marcio.springbootapi.domain.City;

public interface CityRepository extends JpaRepository<City, Integer>{

//	@Query("SELECT obj FROM City WHERE obj.state.id = :stateId ORDER BY obj.name")
	@Transactional(readOnly=true)
	List<City> findByStateIdOrderByNameAsc(Integer estadoId);
}
