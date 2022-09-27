package com.lins.works.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	
	
	

	
}
