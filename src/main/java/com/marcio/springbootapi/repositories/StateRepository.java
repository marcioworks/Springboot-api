package com.marcio.springbootapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcio.springbootapi.domain.State;

public interface StateRepository extends JpaRepository<State, Integer> {

}
