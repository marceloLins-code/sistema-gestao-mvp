package com.lins.works.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.works.domain.entity.Cargo;
import com.lins.works.domain.entity.Setor;
import com.lins.works.domain.entity.Trabalhador;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
import com.lins.works.domain.exception.NegocioException;
import com.lins.works.domain.repository.TrabalhadorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TrabalhadorService {

	private static final String ID_DIGITADO_NÃO_EXISTE = "Id de numero %d, digitado Não Existe";

	private static final String NAO_E_POSSIVEL_EXCLUIR_TRABALHADOR = "Trabalhador de Id %d em uso, Não é possivel excluir";

	private TrabalhadorRepository trabalhadorRepository;

	private CargoService cargoService;
	
	private SetorService setorSevice;
	
	
	
	
	@Transactional
	public Trabalhador adicionarTrabalhador(Trabalhador trabalhador) {
		
		Long cargoId = trabalhador.getCargo().getId();

		Cargo cargo = cargoService.buscarOuFalhar(cargoId);


		Long setorId = trabalhador.getSetor().getId();

		Setor setor = setorSevice.buscarOuFalhar(setorId);

		
		boolean exist = trabalhadorRepository.existsByCpf(trabalhador.getCpf());

		if (exist == true) {
			throw new RuntimeException("CPF já cadastrado");
		}
		
		trabalhador.setCargo(cargo);
		trabalhador.setSetor(setor);
		
		
		return trabalhadorRepository.save(trabalhador);

	}
	
	
	@Transactional
	public void remover(Long trabalhadorId) {
		try {
			trabalhadorRepository.deleteById(trabalhadorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(NAO_E_POSSIVEL_EXCLUIR_TRABALHADOR, trabalhadorId));
		}

		catch (EmptyResultDataAccessException e) {
			throw new NegocioException(e.getMessage());
		
		}
	}
	
	
	@Transactional
	public Trabalhador atualizarTrabalhador(Long trabalhadorId, Trabalhador trabalhador) {

		Trabalhador trabalhadorAtual = buscarOuFalhar(trabalhadorId);

		BeanUtils.copyProperties(trabalhador, trabalhadorAtual, "id");

		try {
			return trabalhadorRepository.save(trabalhadorAtual);

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

	}

	public Trabalhador buscarOuFalhar(Long trabalhadorId) {
		return trabalhadorRepository.findById(trabalhadorId).orElseThrow(
				() -> new NegocioException(String.format(ID_DIGITADO_NÃO_EXISTE, trabalhadorId)));
	}

}
