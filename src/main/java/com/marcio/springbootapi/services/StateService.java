package com.marcio.springbootapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.State;
import com.marcio.springbootapi.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepo;

	public List<State> findAll(){
		return stateRepo.findAllByOrderByName();
	}
}
