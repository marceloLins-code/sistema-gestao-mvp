package com.lins.works.domain.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.works.domain.entity.Trabalhador;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
import com.lins.works.domain.repository.TrabalhadorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrabalhadorService {

	private TrabalhadorRepository trabalhadorRepository;

	public Trabalhador adicionarTrabalhador(Trabalhador trabalhador) {

		boolean exist = trabalhadorRepository.existsByCpf(trabalhador.getCpf());

		if (exist == true) {
			throw new RuntimeException("CPF já cadastrado");
		}

		return trabalhadorRepository.save(trabalhador);

	}

	public void remover(Long trabalhadorId) {
		try {
			trabalhadorRepository.deleteById(trabalhadorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Trabalhador de Id %d em uso, Não é possivel excluir", trabalhadorId));
		}

		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Id de numero %d, digitado Não Existe", trabalhadorId));
		}
	}

}
