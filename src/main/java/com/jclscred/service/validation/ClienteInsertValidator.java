package com.jclscred.service.validation;

import com.jclscred.dto.ClienteNewDTO;
import com.jclscred.repository.ClienteRepository;
import com.jclscred.resource.ValidationMessage;
import com.jclscred.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert clienteInsert) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<ValidationMessage> list = new ArrayList<>();

        if(BR.isValidCPF(objDto.getCpf())) {
            list.add(new ValidationMessage("cpf", "CPF inválido!"));
        }

        if(clienteRepository.findByEmail(objDto.getEmail()) != null) {
            list.add(new ValidationMessage("email", "E-Mail já cadastrado!"));
        }

        // Inserindo erros de validacao na lista
        list.forEach( e -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        });

        return list.isEmpty();
    }
}

