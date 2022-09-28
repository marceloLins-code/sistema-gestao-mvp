package com.lins.works.api.controller;

import java.util.List;

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

import com.lins.works.domain.entity.Trabalhador;
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
	public Trabalhador buscarId(@PathVariable Long trabalhadorId) {
		return trabalhadorService.buscarOuFalhar(trabalhadorId);
	}
	
	@PutMapping("/{trabalhadorId}")
	public Trabalhador atualizarTrabalhador(@PathVariable Long trabalhadorId, @RequestBody Trabalhador trabalhador) {

		return trabalhadorService.novoTrabalhador(trabalhadorId, trabalhador);
	}

	@DeleteMapping("/{trabalhadorId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long trabalhadorId) {
		trabalhadorService.remover(trabalhadorId);
	}

}
