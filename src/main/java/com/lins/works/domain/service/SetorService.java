package com.lins.works.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.lins.works.domain.entity.Setor;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
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

	public void remover(Long setorId) {
		try {
			setorRepository.deleteById(setorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Setor de Id %d em uso, Não é possivel excluir", setorId));
		}

		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Id de numero %d, digitado Não Existe", setorId));
		}
	}

}