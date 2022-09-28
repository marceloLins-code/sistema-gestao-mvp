package com.lins.works.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lins.works.domain.entity.Setor;
import com.lins.works.domain.exception.EntidadeEmUsoException;
import com.lins.works.domain.exception.EntidadeNaoEncontradaException;
import com.lins.works.domain.repository.SetorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SetorService {

	private static final String SETOR_EM_USO = "Setor de Id %d em uso, Não é possivel excluir";
	private static final String MSG_SETOR_NAO_ENCONTRADO = "Setor de id %d não encontrado";
	private SetorRepository setorRepository;

	public Setor novoSetor(Setor setor) {
		
		//buscarOuFalhar(setor.getId());
		
		boolean exist = setorRepository.existsByNome(setor.getNome());

		if (exist == true) {
			throw new RuntimeException("Setor já cadastrado");
		}		

		return setorRepository.save(setor);
	}
	
	

	@Transactional
	public Setor atualizarSetor(Long setorId, Setor setor) {
		
		Setor setorAtual = buscarOuFalhar(setorId); 

		BeanUtils.copyProperties(setor, setorAtual, "id");

		return setorRepository.save(setorAtual);
	}
	

	public void remover(Long setorId) {
		try {
			setorRepository.deleteById(setorId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(SETOR_EM_USO, setorId));
		}

		catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_SETOR_NAO_ENCONTRADO, setorId));
		}
	}
	
	
	public Setor buscarOuFalhar(Long setorId) {
		return setorRepository.findById(setorId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_SETOR_NAO_ENCONTRADO, setorId)));
	}

}