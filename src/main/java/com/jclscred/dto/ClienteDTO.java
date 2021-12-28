package com.jclscred.dto;

import com.jclscred.domain.Cliente;
import com.jclscred.service.validation.ClienteUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

@ClienteUpdate
@Getter
@Setter
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "Não pode estar vazio!")
	@Length(min = 5, max = 120, message = "O tamaho deve estar entre 5 e 120 caracteres!")	
	private String nome;
	
	@NotEmpty(message = "Não pode estar vazio!")
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

	// Endereço
	@NotEmpty(message = "O Logradouro não pode ser vazio!")
	private String logradouro;

	@NotEmpty(message = "O Numero não pode ser vazio!")
	private String numero;

	private String complemento;

	@NotEmpty(message = "O Bairro não pode ser vazio!")
	private String bairro;

	@NotEmpty(message = "O CEP não pode ser vazio!")
	private String cep;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.cpf = cliente.getCpf();
		this.rg = cliente.getRg();
		this.renda = cliente.getRenda();
		this.senha = cliente.getSenha();
		this.logradouro = cliente.getLogradouro();
		this.numero = cliente.getNumero();
		this.complemento = cliente.getComplemento();
		this.bairro = cliente.getBairro();
		this.cep = cliente.getCep();
	}

}
