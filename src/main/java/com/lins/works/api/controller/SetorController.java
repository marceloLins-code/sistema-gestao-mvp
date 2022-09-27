package com.lins.works.api.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lins.works.domain.entity.Setor;
import com.lins.works.domain.repository.SetorRepository;
import com.lins.works.domain.service.SetorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/setores")
public class SetorController {
	
	private SetorRepository setorRepository;

	
	private SetorService setorService;
	
	
	@GetMapping
	public List<Setor> buscarTodos(){
		return setorRepository.findAll();	
	}
	
	@GetMapping("/{setorId}")
	public ResponseEntity<Setor> buscar(@PathVariable Long setorId) {
		return setorRepository.findById(setorId).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	public Setor adicionarSetor(@RequestBody Setor setor) {
		
		setorRepository.existsByNome(setor.getNome());
	
		return setorService.novoSetor(setor); 
		
		
	}
	
	@Transactional
	@PutMapping("/{setorId}")
	public Setor atualizar(@PathVariable Long setorId, @RequestBody Setor setor){		
				
		return setorService.atualizarSetor(setorId, setor);
	}
	
	
	
	
	
}
		
	

	