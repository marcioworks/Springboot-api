package com.marcio.springbootapi.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.marcio.springbootapi.domain.Address;
import com.marcio.springbootapi.domain.City;
import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.domain.enums.ClientType;
import com.marcio.springbootapi.domain.enums.Profile;
import com.marcio.springbootapi.dtos.ClientDto;
import com.marcio.springbootapi.dtos.NewClientDto;
import com.marcio.springbootapi.repositories.AddressRepository;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.security.UserSS;
import com.marcio.springbootapi.services.exceptions.AuthorizationException;
import com.marcio.springbootapi.services.exceptions.DataIntegrityException;
import com.marcio.springbootapi.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
 
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ClientRepository repo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;

	public List<Client> getClients() {
		return repo.findAll();
	}

	public Client getById(Integer id) {
		
		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Client> client = repo.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Client not found! id: " + Client.class.getName()));
	}

	public Client findByEmail(String email) {

		UserSS user = UserService.authenticated();
		if(user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Client cli = repo.findByEmail(email);
		if(cli == null) {
			throw new ObjectNotFoundException("User not found"+ user.getId()+" , type: "+ Client.class);
		}
		
		return cli;
	}
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepo.saveAll(obj.getAddresses());

		return obj;
	}

	public Client update(Client obj) {
		Client newClient = getById(obj.getId());
		updateData(newClient, obj);
		return repo.save(newClient);
	}

	private void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());

	}

	public void delete(Integer id) {
		getById(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cant delete a Client that have Orders!");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDto(ClientDto obj) {
		return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null,null);
	}

	public Client fromDto(NewClientDto obj) {
		Client cli = new Client(null, obj.getName(), obj.getEmail(), obj.getCpfOrCnpj(),
				ClientType.toEnum(obj.getType()),pe.encode(obj.getPassword()));
		City city = new City(obj.getCityId(), null, null);
		Address address = new Address(null, obj.getStreet(), obj.getNumber(), obj.getComplement(), obj.getNeighborood(),
				obj.getZipcode(), city, cli);
		cli.getAddresses().add(address);
		cli.getPhones().add(obj.getPhone1());
		if (obj.getPhone2() != null) {
			cli.getPhones().add(obj.getPhone2());
		}
		if (obj.getPhone3() != null) {
			cli.getPhones().add(obj.getPhone3());
		}

		return cli;
	}
	
	public URI uploadProfilePicture(MultipartFile multipartfile) {
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartfile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

	}

}
