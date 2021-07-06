package com.marcio.springbootapi.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private MailService mailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Client cli = clientRepo.findByEmail(email);
		if(cli == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String password = newPassword();
		cli.setPassword(pe.encode(password));
		clientRepo.save(cli);
		mailService.sendNewPasswordEmail(cli,  password);
		
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // generate a digit
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {// generate a uppercase letter
			return (char) (rand.nextInt(26) + 65);
		} else {// generate a lowercase letter
			return (char) (rand.nextInt(26) + 97);
		}

	}
}
