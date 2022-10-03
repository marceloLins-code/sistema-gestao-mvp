package com.lins.works.domain.exception;

public class CargoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CargoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CargoNaoEncontradaException(Long cargoId) {
		this(String.format("Cargo de id %d não encontrado ", cargoId));
	}

}