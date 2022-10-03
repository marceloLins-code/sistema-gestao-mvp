package com.lins.works.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SetorNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public SetorNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public SetorNaoEncontradaException(Long setorId) {
		this(String.format("Setor de id %d não encontrado ", setorId));
	}

}