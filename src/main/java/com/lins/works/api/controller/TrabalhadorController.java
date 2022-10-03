package com.lins.works.api.controller;

import java.util.List;

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

import com.lins.works.domain.entity.Trabalhador;
import com.lins.works.domain.exception.CargoNaoEncontradaException;
import com.lins.works.domain.exception.NegocioException;
import com.lins.works.domain.exception.SetorNaoEncontradaException;
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
	@ResponseStatus(HttpStatus.CREATED)
	public Trabalhador novoTrab(@RequestBody Trabalhador trabalhador) {
		try {
			return trabalhadorService.adicionarTrabalhador(trabalhador);

		} catch (SetorNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());

		} catch (CargoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@GetMapping("/{trabalhadorId}")
	public Trabalhador buscarId(@PathVariable Long trabalhadorId) {
		return trabalhadorService.buscarOuFalhar(trabalhadorId);
	}

	@PutMapping("/{trabalhadorId}")
	public Trabalhador atualizarTrabalhador(@PathVariable Long trabalhadorId, @RequestBody Trabalhador trabalhador) {
		try {

			Trabalhador trabalhadorAtual = trabalhadorService.buscarOuFalhar(trabalhadorId);

			BeanUtils.copyProperties(trabalhador, trabalhadorAtual, "id");
			return trabalhadorService.adicionarTrabalhador(trabalhadorAtual);

		} catch (SetorNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		
		
	} catch (CargoNaoEncontradaException e) {
		throw new NegocioException(e.getMessage());
	}


	}

	@DeleteMapping("/{trabalhadorId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long trabalhadorId) {
		trabalhadorService.remover(trabalhadorId);
	}

}
