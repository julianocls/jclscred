package com.jclscred.service.validation;

import com.jclscred.domain.Cliente;
import com.jclscred.dto.ClienteDTO;
import com.jclscred.repository.ClienteRepository;

import com.jclscred.resource.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(ClienteUpdate clienteUpdate) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<ValidationMessage> list = new ArrayList<>();

		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long urlId = Long.parseLong(map.get("id"));
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		if (cliente != null && !cliente.getId().equals(urlId)) {
			list.add(new ValidationMessage("email", "E-Mail já cadastrado para o cliente [" + cliente.getNome() + "]!"));
		}

		// Inserindo erros de validacao na lista
		list.forEach( e -> {
			    context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		});

		return list.isEmpty();
	}
}
