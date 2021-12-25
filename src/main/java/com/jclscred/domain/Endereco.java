package com.jclscred.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O cpf não pode ser vazio!")
	@Column(length = 60)
	private String logradouro;

	@NotEmpty(message = "O cpf não pode ser vazio!")
	@Column(length = 15)
	private String numero;

	@Column(length = 120)
	private String complemento;

	@NotEmpty(message = "O cpf não pode ser vazio!")
	@Column(length = 60)
	private String bairro;

	@NotEmpty(message = "O cpf não pode ser vazio!")
	@Column(length = 10)
	private String cep;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Endereco() {
	}

	public Endereco(Long id, String logradouro, String numero, String complemento, String bairro, String cep,
                    Cliente cliente) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cliente = cliente;
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
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}