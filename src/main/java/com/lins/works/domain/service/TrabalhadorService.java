package com.lins.works.domain.service;

import org.springframework.stereotype.Service;

import com.lins.works.domain.entity.Trabalhador;
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

}
