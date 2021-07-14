package com.marcio.springbootapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.City;
import com.marcio.springbootapi.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepo;
	
	public List<City> findAll(Integer stateId){
		return cityRepo.findByStateIdOrderByNameAsc(stateId);
	}
	
}
