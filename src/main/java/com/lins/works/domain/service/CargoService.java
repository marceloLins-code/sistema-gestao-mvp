package com.lins.works.domain.service;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.works.domain.entity.Cargo;
import com.lins.works.domain.entity.Setor;
import com.lins.works.domain.exception.CargoNaoEncontradaException;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.repository.CargoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CargoService {

	private static final String CARGO_EM_USO = "Cargo de Id %d em uso, Não é possivel excluir";


	private CargoRepository cargoRepository;
	
	private SetorService setorService;
	
	

	public Cargo novoCargo(Cargo cargo) {	
		
		boolean existCargoNome = cargoRepository.existsByNome(cargo.getNome());

		boolean existCargoSetor = cargoRepository.existsByNomeContainingAndSetorId(cargo.getNome(),
				cargo.getSetor().getId());

		if (existCargoNome == true && existCargoSetor == false) {
			throw new RuntimeException("Cargo nâo correspode a este setor ou setor inexistente!");
		}
		
		long setorId = cargo.getSetor().getId(); 		
		Setor setor =setorService.buscarOuFalhar(setorId);	
		
		cargo.setSetor(setor);

		return cargoRepository.save(cargo);
	}

	
	@Transactional
	public void remover(Long cargoId) {
		try {
			cargoRepository.deleteById(cargoId);

		} 

		catch (EmptyResultDataAccessException e) {
			throw new CargoNaoEncontradaException(cargoId);
		}
		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException
			(String.format(CARGO_EM_USO, cargoId));
		}
	}	

	public Cargo buscarOuFalhar(Long cargoId) {

		return cargoRepository.findById(cargoId).orElseThrow(
				() -> new CargoNaoEncontradaException(cargoId));
	}

}
