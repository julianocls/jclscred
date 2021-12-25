package com.jclscred.service.validation;

import com.jclscred.dto.ClienteNewDTO;
import com.jclscred.repository.ClienteRepository;
import com.jclscred.resource.FieldMessage;
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
        List<FieldMessage> list = new ArrayList<>();

        if(BR.isValidCPF(objDto.getCpf())) {
            list.add(new FieldMessage("cpf", "CPF inválido!"));
        }

        if(clienteRepository.findByEmail(objDto.getEmail()) != null) {
            list.add(new FieldMessage("email", "E-Mail já cadastrado!"));
        }

        // Inserindo erros de validacao na lista
        list.forEach( e -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        });

//        for (FieldMessage e : list) {
//            context.disableDefaultConstraintViolation();
//            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
//                    .addConstraintViolation();
//        }

        return list.isEmpty();
    }
}

