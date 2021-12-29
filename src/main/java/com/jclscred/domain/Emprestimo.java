package com.jclscred.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Emprestimo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(precision = 13, scale = 2)
	private BigDecimal valor;

	private LocalDate dataPrimeiraParcela;
	private Integer quantidadeParcelas;
	private LocalDate dataEmprestimo;

	@JsonIgnore
	@OneToMany(mappedBy = "emprestimo")
	private List<ParcelaEmprestimo> parcelas = new ArrayList<>();

	public Emprestimo() {
	}

	public Emprestimo(Long id, BigDecimal valor, LocalDate dataPrimeiraParcela, Integer quantidadeParcelas, LocalDate dataEmprestimo) {
		this.id = id;
		this.valor = valor;
		this.dataPrimeiraParcela = dataPrimeiraParcela;
		this.quantidadeParcelas = quantidadeParcelas;
		this.dataEmprestimo = dataEmprestimo;
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
		Emprestimo other = (Emprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}