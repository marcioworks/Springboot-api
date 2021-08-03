package com.marcio.springbootapi.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.dtos.ClientDto;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.resources.exceptions.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDto> {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private ClientRepository clientRepo;
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDto objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		
		
		Client aux = clientRepo.findByEmail(objDto.getEmail());
		if(aux !=null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email: ", "  Email already exist"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}