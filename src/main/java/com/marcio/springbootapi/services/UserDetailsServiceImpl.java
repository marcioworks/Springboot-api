package com.marcio.springbootapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private ClientRepository clientRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client cli = clientRepo.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(),cli.getPassword(),cli.getPerfis());
	}

}
