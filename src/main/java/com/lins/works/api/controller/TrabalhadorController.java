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

import com.lins.works.domain.assembler.TrabalhadorAssemblerModel;
import com.lins.works.domain.entity.Trabalhador;
import com.lins.works.domain.exception.CargoNaoEncontradaException;
import com.lins.works.domain.exception.NegocioException;
import com.lins.works.domain.exception.SetorNaoEncontradaException;
import com.lins.works.domain.repository.TrabalhadorRepository;
import com.lins.works.domain.service.TrabalhadorService;
import com.lins.works.modelDTO.TrabalhadorModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/trabalhadores")
public class TrabalhadorController {

	private TrabalhadorRepository trabalhadorRepository;

	private TrabalhadorService trabalhadorService;

	private TrabalhadorAssemblerModel trabalhadorAssemblerModel;

	@GetMapping
	public List<TrabalhadorModel> buscarTodos() {
		return trabalhadorAssemblerModel.toCollectionModelo(trabalhadorRepository.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TrabalhadorModel novoTrab(@RequestBody Trabalhador trabalhador) {
		try {
			return trabalhadorAssemblerModel.toModel(trabalhadorService.adicionarTrabalhador(trabalhador));

		} catch (SetorNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());

		} catch (CargoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@GetMapping("/{trabalhadorId}")
	public TrabalhadorModel buscarId(@PathVariable Long trabalhadorId) {

		Trabalhador trabalhador = trabalhadorService.buscarOuFalhar(trabalhadorId);
		
		return trabalhadorAssemblerModel.toModel(trabalhador);
	}

	@PutMapping("/{trabalhadorId}")
	public TrabalhadorModel atualizarTrabalhador(@PathVariable Long trabalhadorId,
			@RequestBody Trabalhador trabalhador) {
		try {

			Trabalhador trabalhadorAtual = trabalhadorService.buscarOuFalhar(trabalhadorId);

			BeanUtils.copyProperties(trabalhador, trabalhadorAtual, "id");
			return trabalhadorAssemblerModel.toModel(trabalhadorService.adicionarTrabalhador(trabalhadorAtual));

		} catch (SetorNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);

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
