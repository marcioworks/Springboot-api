package com.marcio.springbootapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.domain.Product;
import com.marcio.springbootapi.repositories.CategoryRepository;
import com.marcio.springbootapi.repositories.ProductRepository;

@SpringBootApplication
public class SpringbootApiApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ProductRepository productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category cat1 = new Category(1, "Computing");
		Category cat2 = new Category(2, "Office");
		
		Product p1 = new Product(null, "NoteBook", 2000.0);
		Product p2 = new Product(null, "Printer", 800.0);
		Product p3 = new Product(null, "Mouse", 90.0);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepo.saveAll(Arrays.asList(cat1,cat2));
		productRepo.saveAll(Arrays.asList(p1,p2,p3));
	}

}
