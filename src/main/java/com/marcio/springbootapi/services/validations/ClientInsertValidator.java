package com.marcio.springbootapi.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.marcio.springbootapi.domain.Client;
import com.marcio.springbootapi.domain.enums.ClientType;
import com.marcio.springbootapi.dtos.NewClientDto;
import com.marcio.springbootapi.repositories.ClientRepository;
import com.marcio.springbootapi.resources.exceptions.FieldMessage;
import com.marcio.springbootapi.services.validations.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, NewClientDto> {
	
	@Autowired
	private ClientRepository clientRepo;
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(NewClientDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getType().equals(ClientType.PHYSICALPERSON.getCod())&& !BR.isValidCPF(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CPF"));
		}
		
		if(objDto.getType().equals(ClientType.LEGALPERSON.getCod())&& !BR.isValidCNPJ(objDto.getCpfOrCnpj())) {
			list.add(new FieldMessage("cpfOrCnpj", "Invalid CNPJ"));
		}
		
		Client aux = clientRepo.findByEmail(objDto.getEmail());
		if(aux !=null) {
			list.add(new FieldMessage("email", "Email already exist"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}