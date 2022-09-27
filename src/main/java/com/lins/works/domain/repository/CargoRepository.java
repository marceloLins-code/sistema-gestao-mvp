package com.lins.works.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.lins.works.domain.entity.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

	ResponseEntity<Cargo> save(Optional<Cargo> cargoAtual);	
	
	boolean existsByNome(String nome);

	boolean existsByNomeContainingAndSetorId(String nome, Long id);

	
}
