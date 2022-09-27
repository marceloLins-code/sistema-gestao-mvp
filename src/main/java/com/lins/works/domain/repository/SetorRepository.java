package com.lins.works.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.works.domain.entity.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
	
//	@Query("from Setor where nome like %:nome%")
//	List<Setor> consultarPorNome(String nome);
	
	boolean existsByNome(String nome);

	boolean existsById(Long id);
	
		

}