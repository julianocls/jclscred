package com.jclscred.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 120)
	private String nome;

	@Column(unique = true, length = 60)
	private String email;

	@Column(length = 11)
	private String cpf;

	@Column(length = 20)
	private String rg;

	@Column(precision = 13, scale = 2)
	private BigDecimal renda;

	@JsonIgnore
	@NotEmpty(message = "O senha não pode ser vazio!")
	private String senha;

	// Endereço
	@NotEmpty(message = "O Logradouro não pode ser vazio!")
	@Column(length = 60)
	private String logradouro;

	@NotEmpty(message = "O Numero não pode ser vazio!")
	@Column(length = 15)
	private String numero;

	@Column(length = 120)
	private String complemento;

	@NotEmpty(message = "O Bairro não pode ser vazio!")
	@Column(length = 60)
	private String bairro;

	@NotEmpty(message = "O CEP não pode ser vazio!")
	@Column(length = 10)
	private String cep;

	public Cliente() {
	}

	public Cliente(Long id, String nome, String email, String cpf, String rg, BigDecimal renda, String senha,
				   String logradouro, String numero, String complemento, String bairro, String cep) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}