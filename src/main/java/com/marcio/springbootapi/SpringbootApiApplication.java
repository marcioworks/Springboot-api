package com.marcio.springbootapi;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcio.springbootapi.domain.Address;
import com.marcio.springbootapi.domain.BilletPayment;
import com.marcio.springbootapi.domain.CardPayment;
import com.marcio.springbootapi.domain.Category;
import com.marcio.springbootapi.domain.City;
import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.domain.ItemPedido;
import com.marcio.springbootapi.domain.Payment;
import com.marcio.springbootapi.domain.Pedido;
import com.marcio.springbootapi.domain.Product;
import com.marcio.springbootapi.domain.State;
import com.marcio.springbootapi.domain.enums.ClientType;
import com.marcio.springbootapi.domain.enums.PaymentState;
import com.marcio.springbootapi.repositories.AddressRepository;
import com.marcio.springbootapi.repositories.CategoryRepository;
import com.marcio.springbootapi.repositories.CityRepository;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.repositories.ItemPedidoRepository;
import com.marcio.springbootapi.repositories.OrderRepository;
import com.marcio.springbootapi.repositories.PaymentRepository;
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
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Sports");
		Category cat4 = new Category(null, "Decoration");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Clothing");
		Category cat7 = new Category(null, "Shoes");

		Product p1 = new Product(null, "NoteBook", 2000.0);
		Product p2 = new Product(null, "Printer", 800.0);
		Product p3 = new Product(null, "Mouse", 90.0);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepo.saveAll(Arrays.asList(p1, p2, p3));

		State stt1 = new State(null, "Minas Gerais");
		State stt2 = new State(null, "São Paulo");

		City ct1 = new City(null, "Uberlândia", stt1);
		City ct2 = new City(null, "São paulo", stt2);
		City ct3 = new City(null, "Campinas", stt2);

		stt1.getCities().addAll(Arrays.asList(ct1));
		stt2.getCities().addAll(Arrays.asList(ct2, ct3));

		stateRepo.saveAll(Arrays.asList(stt1, stt2));
		cityRepo.saveAll(Arrays.asList(ct1, ct2, ct3));

		Client cl1 = new Client(null, "Marcio Silva", "marcio@teste.com", "02222222123", ClientType.PHYSICALPERSON);

		cl1.getPhones().addAll(Arrays.asList("85998989898", "85987878787"));

		Address add1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220824", ct1, cl1);
		Address add2 = new Address(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", ct2, cl1);

		cl1.getAddresses().addAll(Arrays.asList(add1, add2));

		clientRepo.saveAll(Arrays.asList(cl1));
		addressRepo.saveAll(Arrays.asList(add1, add2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido order1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, add1);
		Pedido order2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, add2);

		Payment paym1 = new CardPayment(null, PaymentState.PAYED, order1, 6);
		order1.setPayment(paym1);

		Payment paym2 = new BilletPayment(null, PaymentState.PENDING, order2, sdf.parse("20/10/2017 00:00"), null);
		order2.setPayment(paym2);

		cl1.getOrders().addAll(Arrays.asList(order1, order2));

		orderRepo.saveAll(Arrays.asList(order1, order2));
		paymentRepo.saveAll(Arrays.asList(paym1, paym2));

		ItemPedido itp1 = new ItemPedido(order1, p1, 0.00, 1, 2000.0);
		ItemPedido itp2 = new ItemPedido(order1, p3, 0.00, 2, 80.0);
		ItemPedido itp3 = new ItemPedido(order2, p2, 100.00, 1, 800.0);

		order1.getItens().addAll(Arrays.asList(itp1, itp2));
		order2.getItens().addAll(Arrays.asList(itp3));

		p1.getItens().addAll(Arrays.asList(itp1));
		p2.getItens().addAll(Arrays.asList(itp3));
		p3.getItens().addAll(Arrays.asList(itp2));

		itemPedidoRepository.saveAll(Arrays.asList(itp1, itp2, itp3));

	}

}
