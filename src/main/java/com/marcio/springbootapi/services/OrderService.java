package com.marcio.springbootapi.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcio.springbootapi.domain.BilletPayment;
import com.marcio.springbootapi.domain.ItemPedido;
import com.marcio.springbootapi.domain.Pedido;
import com.marcio.springbootapi.domain.enums.PaymentState;
import com.marcio.springbootapi.repositories.ItemPedidoRepository;
import com.marcio.springbootapi.repositories.OrderRepository;
import com.marcio.springbootapi.repositories.PaymentRepository;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BilletService billetService;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ItemPedidoRepository itemRepo;
	
	public Pedido getById(Integer id) {
		Optional<Pedido> pedido = repo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("pedido not Found! id: "+ Pedido.class.getName()));
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof BilletPayment) {
			BilletPayment bp = (BilletPayment) obj.getPayment();
			billetService.FillBilletPayment(bp, obj.getInstante());
		}
		
		obj = repo.save(obj);
		paymentRepo.save(obj.getPayment());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(productService.getById(ip.getProduto().getId()).getPrice());
			ip.setPedido(obj);
		}
		
		itemRepo.saveAll(obj.getItens());
		return obj;
		
	}
	
}
