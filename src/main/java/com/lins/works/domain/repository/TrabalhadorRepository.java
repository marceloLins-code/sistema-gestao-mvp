package com.lins.works.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lins.works.domain.entity.Trabalhador;

@Repository
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {

	Optional<Trabalhador> findById(Integer cpf);

	List<Trabalhador> findByCpf(Integer cpf);
	
	boolean existsByCpf(Integer cpf);

}
