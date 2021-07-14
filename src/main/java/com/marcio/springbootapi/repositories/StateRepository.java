package com.marcio.springbootapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.marcio.springbootapi.domain.State;

public interface StateRepository extends JpaRepository<State, Integer> {

	@Transactional(readOnly=true)
	List<State> findAllByOrderByName();
}
