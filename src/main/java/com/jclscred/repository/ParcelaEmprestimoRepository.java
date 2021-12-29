package com.jclscred.repository;

import com.jclscred.domain.ParcelaEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaEmprestimoRepository extends JpaRepository<ParcelaEmprestimo, Long> {

//	@Transactional(readOnly = false)
//	public Cliente findByEmail(String email);

}
