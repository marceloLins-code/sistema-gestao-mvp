package com.lins.works.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.works.domain.entity.Cargo;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
import com.lins.works.domain.repository.CargoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CargoService {

	private CargoRepository cargoRepository;

	public Cargo novoCargo(Cargo cargo) {

		boolean existCargoNome = cargoRepository.existsByNome(cargo.getNome());

		boolean existCargoSetor = cargoRepository.existsByNomeContainingAndSetorId(cargo.getNome(),
				cargo.getSetor().getId());

		if (existCargoNome == true && existCargoSetor == false) {

			throw new RuntimeException("Cargo nâo correspode a este setor!");

		}

		return cargoRepository.save(cargo);
	}

	public void remover(Long cargoId) {
		try {
			cargoRepository.deleteById(cargoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cargo de Id %d em uso, Não é possivel excluir", cargoId));
		}

		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Id de numero %d, digitado Não Existe", cargoId));
		}
	}

}
