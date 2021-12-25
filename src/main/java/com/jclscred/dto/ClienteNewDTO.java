package com.jclscred.dto;

import com.jclscred.service.validation.ClienteInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ClienteInsert
public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 120, message = "O tamanho deve estar entre 5 e 120 caracteres!")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Email(message = "E-Mail inválido!")
	@Length(min = 5, max = 60, message = "O tamanho deve estar entre 5 e 60 caracteres!")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 14, max = 14, message = "O tamanho deve ser 14 caracteres!")
	private String cpf;

	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 13, max = 20, message = "O tamanho deve estar entre 13 e 20 caracteres!")
	private String rg;

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

	private Integer cidadeId;

	public ClienteNewDTO() {
	}

}
