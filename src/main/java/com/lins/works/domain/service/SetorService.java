package com.lins.works.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.lins.works.domain.entity.Setor;
import com.lins.works.domain.repository.SetorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SetorService {

	private SetorRepository setorRepository;
	
	

	public Setor novoSetor(Setor setor) {
		
		 boolean exist = setorRepository.existsByNome(setor.getNome());
		
		if (exist == true) {
			throw new RuntimeJsonMappingException("Nome já cadastrado");
		}	
		
		return setorRepository.save(setor);
		}	

	@Transactional
	public Setor atualizarSetor(Long setorId, Setor setor) {

		Optional<Setor> setorAtual = setorRepository.findById(setorId);

		BeanUtils.copyProperties(setor, setorAtual, "id");

		return setorRepository.save(setor);
	}

}