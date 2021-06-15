package com.marcio.springbootapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.repositories.CategoryRepository;

@SpringBootApplication
public class SpringbootApiApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category cat1 = new Category(1, "Eletronics");
		Category cat2 = new Category(2, "Sports");
		
		categoryRepo.saveAllAndFlush(Arrays.asList(cat1,cat2));
	}

}
