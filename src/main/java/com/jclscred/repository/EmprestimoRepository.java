package com.jclscred.repository;

import com.jclscred.domain.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

//	@Transactional(readOnly = false)
//	public Cliente findByEmail(String email);

}
