package com.marcio.springbootapi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcio.springbootapi.domain.Address;
import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.domain.City;
import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.domain.Product;
import com.marcio.springbootapi.domain.State;
import com.marcio.springbootapi.domain.enums.ClientType;
import com.marcio.springbootapi.repositories.AddressRepository;
import com.marcio.springbootapi.repositories.CategoryRepository;
import com.marcio.springbootapi.repositories.CityRepository;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.repositories.ProductRepository;
import com.marcio.springbootapi.repositories.StateRepository;

@SpringBootApplication
public class SpringbootApiApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private ClientRepository clientRepo;
	@Autowired
	private AddressRepository addressRepo;
	
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
		
		State stt1 = new State(null, "Minas Gerais");
		State stt2 = new State(null, "São Paulo");
		
		City ct1 = new City(null, "Uberlândia", stt1);
		City ct2 = new City(null, "São paulo", stt2);
		City ct3 = new City(null, "Campinas", stt2);
		
		stt1.getCities().addAll(Arrays.asList(ct1));
		stt2.getCities().addAll(Arrays.asList(ct2,ct3));
		
		stateRepo.saveAll(Arrays.asList(stt1,stt2));
		cityRepo.saveAll(Arrays.asList(ct1,ct2,ct3));
		
		Client cl1 = new Client(null, "Marcio Silva", "marcio@teste.com", "02222222123", ClientType.PHYSICALPERSON);
		
		cl1.getPhones().addAll(Arrays.asList("85998989898","85987878787"));
		
		Address add1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220824", ct1, cl1);
		Address add2 = new Address(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", ct2, cl1);
		
		cl1.getAddresses().addAll(Arrays.asList(add1,add2));
		
		clientRepo.saveAll(Arrays.asList(cl1));
		addressRepo.saveAll(Arrays.asList(add1,add2));
		
		
	}

}
