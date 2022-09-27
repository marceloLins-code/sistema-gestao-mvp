package com.lins.works.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lins.works.domain.entity.Trabalhador;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
import com.lins.works.domain.repository.TrabalhadorRepository;
import com.lins.works.domain.service.TrabalhadorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/trabalhadores")
public class TrabalhadorController {

	private TrabalhadorRepository trabalhadorRepository;

	private TrabalhadorService trabalhadorService;

	@GetMapping
	public List<Trabalhador> buscarTodos() {
		return trabalhadorRepository.findAll();
	}

	@PostMapping
	public Trabalhador novoTrab(@RequestBody Trabalhador trabalhador) {
		return trabalhadorService.adicionarTrabalhador(trabalhador);
	}

	@GetMapping("/{trabalhadorId}")
	public ResponseEntity<Trabalhador> buscarId(@PathVariable Long trabalhadorId) {
		return trabalhadorRepository.findById(trabalhadorId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{trabalhadorId}")
	public ResponseEntity<Trabalhador> remover(@PathVariable Long trabalhadorId) {
		try {
			trabalhadorService.remover(trabalhadorId);

			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
