package com.marcio.springbootapi.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcio.springbootapi.domain.City;
import com.marcio.springbootapi.domain.State;
import com.marcio.springbootapi.dtos.CityDto;
import com.marcio.springbootapi.dtos.StateDto;
import com.marcio.springbootapi.services.CityService;
import com.marcio.springbootapi.services.StateService;

@RestController
@RequestMapping("/state")
public class StateResource {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;

	@GetMapping
	public ResponseEntity<List<StateDto>> findAll() {
		List<State> states = stateService.findAll();
		List<StateDto> list = states.stream().map(s -> new StateDto(s)).collect(Collectors.toList());

		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{stateId}/cities")
	public ResponseEntity<List<CityDto>> findCities(@PathVariable Integer stateId){
		List<City> cities = cityService.findAll(stateId);
		List<CityDto> list = cities.stream().map(c -> new CityDto(c)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(list);
	}
}
