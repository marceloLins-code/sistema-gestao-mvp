package com.lins.works.api.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lins.works.domain.entity.Cargo;
import com.lins.works.domain.exception.NegocioException;
import com.lins.works.domain.exception.SetorNaoEncontradaException;
import com.lins.works.domain.repository.CargoRepository;
import com.lins.works.domain.service.CargoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/cargos")
public class CargoController {

	private CargoRepository cargoRepository;

	private CargoService cargoService;

	@GetMapping
	public List<Cargo> buscarTodos() {
		return cargoRepository.findAll();
	}

	@GetMapping("/{cargoId}")
	public Cargo buscar(@PathVariable Long cargoId) {
		return cargoService.buscarOuFalhar(cargoId);
	}

	@Transactional
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cargo novoCargo(@RequestBody Cargo cargo) {

		try {
			return cargoService.novoCargo(cargo);

		} catch (SetorNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cargoId}")
	public Cargo atualizar(@PathVariable Long cargoId, @RequestBody Cargo cargo) {

		try {

			Cargo cargoAtual = cargoService.buscarOuFalhar(cargoId);

			BeanUtils.copyProperties(cargo, cargoAtual, "id");
			return cargoService.novoCargo(cargoAtual);

		} catch (SetorNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	@DeleteMapping("/{cargoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cargoId) {		
		
		cargoService.remover(cargoId);
	}

	

}