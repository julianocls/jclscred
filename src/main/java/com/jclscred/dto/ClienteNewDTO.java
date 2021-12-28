package com.jclscred.dto;

import com.jclscred.service.validation.ClienteInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ClienteInsert
public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	public ClienteNewDTO() {
	}

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho deve estar entre 5 e 120 caracteres!")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "E-Mail inválido!")
	@Length(min = 5, max = 60, message = "O tamanho deve estar entre 5 e 60 caracteres!")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 11, max = 11, message = "O tamanho deve ser 11 caracteres!")
	private String cpf;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 13, max = 20, message = "O tamanho deve estar entre 13 e 20 caracteres!")
	private String rg;

	@Digits(integer=13, fraction=2, message = "Digite no máximo 13 dígitos com 2 casas decimais!")
	@DecimalMin(value = "0.0", inclusive = false, message = "A renda deve ser uma valor válido!")
	@NotNull(message = "Preenchimento obrigatório!")
	private BigDecimal renda;

	@NotEmpty(message = "O senha não pode ser vazio!")
	private String senha;

	//Enderecos...
	@NotEmpty(message = "Preenchimento obrigatório!")
	private String logradouro;

	@NotEmpty(message = "Preenchimento obrigatório!")
	private String numero;

	private String complemento;

	@NotEmpty(message = "Preenchimento obrigatório!")
	private String bairro;

	@NotEmpty(message = "Preenchimento obrigatório!")
	private String cep;

	public ClienteNewDTO(String nome, String email, String cpf, String rg, BigDecimal renda, String senha,
						 String logradouro, String numero, String complemento, String bairro, String cep) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.rg = rg;
		this.renda = renda;
		this.senha = senha;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
	}
}
