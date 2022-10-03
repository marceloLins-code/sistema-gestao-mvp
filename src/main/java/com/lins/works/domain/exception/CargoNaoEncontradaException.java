package com.lins.works.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CargoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CargoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CargoNaoEncontradaException(Long setorId) {
		this(String.format("Setor de id %d não encontrado ", setorId));
	}

}