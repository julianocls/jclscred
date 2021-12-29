package com.jclscred.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ParcelaEmprestimo {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal valor;
    private Integer numeroParcela;
    private LocalDate dataVencimento;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id")
    private Emprestimo emprestimo;

    public ParcelaEmprestimo(){
    }

    public ParcelaEmprestimo(Integer id, BigDecimal valor, Integer numeroParcela, LocalDate dataVencimento, Emprestimo emprestimo) {
        this.id = id;
        this.valor = valor;
        this.numeroParcela = numeroParcela;
        this.dataVencimento = dataVencimento;
        this.emprestimo = emprestimo;
    }
}
