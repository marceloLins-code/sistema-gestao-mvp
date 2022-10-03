package com.lins.works.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.works.domain.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
	
	/*
	 * @Query("from Cargo c join c.setor") 
	 * List<Cargo> findAll();
	 */

	
	
	boolean existsByNome(String nome);

	boolean existsByNomeContainingAndSetorId(String nome, Long id);

	
}
