package com.jclscred.service.validation;

import com.jclscred.dto.EmprestimoNewDTO;
import com.jclscred.resource.ValidationMessage;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoInsertValidator implements ConstraintValidator<EmprestimoInsert, EmprestimoNewDTO> {

    @Override
    public void initialize(EmprestimoInsert emprestimoInsert) {
    }

    @Override
    public boolean isValid(EmprestimoNewDTO objDto, ConstraintValidatorContext context) {
        List<ValidationMessage> list = new ArrayList<>();

        // valida parcelamento maior que 60 dias
        if(objDto.getQuantidadeParcelas() > 60) {
            list.add(new ValidationMessage("quantidadeParcela", "O Parcelamento máximo é em 60x!"));
        }

        // valida data maior de 3 meses
        if(objDto.getDataPrimeiraParcela().minusMonths(3).isAfter(LocalDate.now())) {
            list.add(new ValidationMessage("dataPrimeiraParcela", "A primeira parcela deve ser no máximo em 3 meses!"));
        }

        // valida data anterior a hoje
        if(objDto.getDataPrimeiraParcela().isBefore(LocalDate.now())) {
            list.add(new ValidationMessage("dataPrimeiraParcela", "A primeira parcela não pode ser em uma data anterior a hoje!"));
        }

        // Inserindo erros de validação na lista
        list.forEach( e -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        });

        return list.isEmpty();
    }
}

