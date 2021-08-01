package com.marcio.springbootapi.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.marcio.springbootapi.domain.enums.Profile;
import com.marcio.springbootapi.repositories.AddressRepository;
import com.marcio.springbootapi.repositories.CategoryRepository;
import com.marcio.springbootapi.repositories.CityRepository;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.repositories.ItemPedidoRepository;
import com.marcio.springbootapi.repositories.OrderRepository;
import com.marcio.springbootapi.repositories.PaymentRepository;
import com.marcio.springbootapi.repositories.ProductRepository;
import com.marcio.springbootapi.repositories.StateRepository;

@Service
public class DbTestService {

	@Autowired
	private BCryptPasswordEncoder pe;
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
	
	public void instatiateTestDb() throws ParseException {
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "House");
		Category cat4 = new Category(null, "Electronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Perfume");
		

		Product p1 = new Product(null, "NoteBook", 2000.0);
		Product p2 = new Product(null, "Printer", 800.0);
		Product p3 = new Product(null, "Mouse", 90.0);
		Product p4 = new Product(null, "Office desk", 300.0);
		Product p5 = new Product(null, "Towel", 50.0);
		Product p6 = new Product(null, "Bed sheet", 200.0);
		Product p7 = new Product(null, "TV true color", 1200.0);
		Product p8 = new Product(null, "Mower", 800.0);
		Product p9 = new Product(null, "Lamp", 100.0);
		Product p10 = new Product(null, "Pendent", 180.0);
		Product p11 = new Product(null, "Shampoo", 90.0);
		

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		List<Product> produtos = new ArrayList<Product>();
		 
		for (int i = 12 ; i < 51; i++ ) {
			Product produto = new Product(null, "Produto " + i, 10.00);
			cat1.getProducts().add(produto);
			produto.getCategories().add(cat1);
			produtos.add(produto);
		}
		

		categoryRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		productRepo.saveAll(produtos);
		State stt1 = new State(null, "Minas Gerais");
		State stt2 = new State(null, "São Paulo");

		City ct1 = new City(null, "Uberlândia", stt1);
		City ct2 = new City(null, "São paulo", stt2);
		City ct3 = new City(null, "Campinas", stt2);

		stt1.getCities().addAll(Arrays.asList(ct1));
		stt2.getCities().addAll(Arrays.asList(ct2, ct3));

		stateRepo.saveAll(Arrays.asList(stt1, stt2));
		cityRepo.saveAll(Arrays.asList(ct1, ct2, ct3));

		Client cl1 = new Client(null, "Marcio Silva", "marcioadsworks@gmail.com", "13669137017", ClientType.PHYSICALPERSON,pe.encode("123"));
		cl1.getPhones().addAll(Arrays.asList("85998989898", "85987878787"));

		Client cl2 = new Client(null, "Maria Silva", "marcioworkar@gmail.com", "55161957050", ClientType.PHYSICALPERSON,pe.encode("123"));
		cl2.addProfile(Profile.ADMIN);
		cl1.getPhones().addAll(Arrays.asList("85998989898", "85987878787"));
		
		Address add1 = new Address(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220824", ct1, cl1);
		Address add2 = new Address(null, "Avenida Matos", "105", "sala 800", "Centro", "38777012", ct2, cl1);
		Address add3 = new Address(null, "Avenida Floriano", "2045", null, "Centro", "38777012", ct2, cl2);

		cl1.getAddresses().addAll(Arrays.asList(add1, add2));
		cl2.getAddresses().addAll(Arrays.asList(add3));

		clientRepo.saveAll(Arrays.asList(cl1,cl2));
		addressRepo.saveAll(Arrays.asList(add1, add2,add3));

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
