package com.jclscred.service;

import com.jclscred.domain.Emprestimo;
import com.jclscred.domain.ParcelaEmprestimo;
import com.jclscred.repository.EmprestimoRepository;
import com.jclscred.repository.ParcelaEmprestimoRepository;
import com.jclscred.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ParcelaEmprestimoRepository parcelaEmprestimoRepository;

    public Emprestimo find(Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        return emprestimo.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado. Id: " + id + ", tipo: " + Emprestimo.class.getName()));
    }

    @Transactional
    public Emprestimo insert(Emprestimo obj) {
        obj.setId(null);
        obj.setDataEmprestimo(LocalDate.now());

        BigDecimal taxaEmprestimo = BigDecimal.valueOf(1.18);

        obj = emprestimoRepository.save(obj);
        for (int i = 0; i < obj.getQuantidadeParcelas(); i++) {
            ParcelaEmprestimo pe = new ParcelaEmprestimo();
            BigDecimal valorParcela = obj.getValor().divide(BigDecimal.valueOf(obj.getQuantidadeParcelas())).multiply(taxaEmprestimo);
            pe.setValor(valorParcela);
            pe.setDataVencimento(obj.getDataPrimeiraParcela().plusMonths(i));
            pe.setEmprestimo(obj);
        }

        parcelaEmprestimoRepository.saveAll(obj.getParcelas());
        return obj;
    }

}
