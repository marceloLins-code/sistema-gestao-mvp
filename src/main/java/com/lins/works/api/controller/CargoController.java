package com.lins.works.api.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lins.works.domain.entity.Cargo;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
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
	public List<Cargo> buscarTodos(){
		return cargoRepository.findAll();	
	}

	
	
	//@Transactional
	@PostMapping
	public void novoCargo(@RequestBody Cargo cargo) {
		 cargoService.novoCargo(cargo);
	}
	

	@GetMapping("/{cargpId}")
	public ResponseEntity<Cargo> buscar(@PathVariable Long cargoId) {
		return cargoRepository.findById(cargoId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@Transactional
	@PutMapping("/{cargoId}")
	public ResponseEntity<Cargo> atualizar(@PathVariable Long cargoId,
			@RequestBody Cargo cozinha) {
		Cargo cargoAtual = cargoRepository.getById(cargoId);
		
		if (cargoAtual != null) {

			BeanUtils.copyProperties(cozinha, cargoAtual, "id");
			
			cargoAtual = cargoService.novoCargo(cargoAtual);
			return ResponseEntity.ok(cargoAtual);
		}
		
		return ResponseEntity.notFound().build();
	}
	

	@DeleteMapping("/{cargoId}")
	public ResponseEntity<Cargo> remover(@PathVariable Long cargoId) {
		try {
			 cargoService.remover(cargoId);
			
			return ResponseEntity.noContent().build();
			
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} 
	}
	

}