package com.lins.works.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.works.domain.entity.Cargo;
import com.lins.works.domain.entity.Setor;
import com.lins.works.domain.exception.CargoNaoEncontradaException;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
import com.lins.works.domain.exception.NegocioException;
import com.lins.works.domain.exception.SetorNaoEncontradaException;
import com.lins.works.domain.repository.SetorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SetorService {

	private static final String SETOR_EM_USO = "Setor de Id %d em uso, Não é possivel excluir";
	

	private SetorRepository setorRepository;
	
	
	

	public Setor novoSetor(Setor setor) {
		
		boolean exist = setorRepository.existsByNome(setor.getNome());

		if (exist == true) {
			throw new RuntimeException("Nome de Setor já Cadastrado");
		}		

		return setorRepository.save(setor);
	}
	
	

	@Transactional
	public Setor atualizarSetor(Long setorId, Setor setor) {
		
		Setor setorAtual = buscarOuFalhar(setorId); 

		BeanUtils.copyProperties(setor, setorAtual, "id");
		
		try {
			return setorRepository.save(setorAtual); 
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

		
	}
	

	public void remover(Long setorId) {
		try {
			setorRepository.deleteById(setorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(SETOR_EM_USO, setorId));
		}

		catch (EmptyResultDataAccessException e) {
			throw new SetorNaoEncontradaException(setorId);
		}
	}
	
	
	public Setor buscarOuFalhar(Long setorId) {

		return setorRepository.findById(setorId).orElseThrow(
				() -> new SetorNaoEncontradaException	(setorId));
	}


}