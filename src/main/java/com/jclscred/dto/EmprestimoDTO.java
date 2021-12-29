package com.jclscred.dto;

import com.jclscred.service.validation.ClienteUpdate;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@ClienteUpdate
@Getter
@Setter
public class EmprestimoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@Digits(integer=13, fraction=2, message = "Digite no máximo 13 dígitos com 2 casas decimais!")
	@DecimalMin(value = "0.0", inclusive = false, message = "O campo valor deve ser uma valor válido!")
	@NotNull(message = "Preenchimento obrigatório!")
	private BigDecimal valor;

	@NotNull(message = "Preenchimento obrigatório!")
	private LocalDate dataPrimeiraParcela;

	@NotNull(message = "Preenchimento obrigatório!")
	private Integer quantidadeParcelas;

	public EmprestimoDTO() {
	}

	public EmprestimoDTO(Long id, BigDecimal valor, LocalDate dataPrimeiraParcela, Integer quantidadeParcelas) {
		this.id = id;
		this.valor = valor;
		this.dataPrimeiraParcela = dataPrimeiraParcela;
		this.quantidadeParcelas = quantidadeParcelas;
	}
}
