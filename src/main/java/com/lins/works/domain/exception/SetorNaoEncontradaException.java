package com.lins.works.domain.exception;

public class SetorNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public SetorNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public SetorNaoEncontradaException(Long setorId) {
		this(String.format("Setor de id %d não encontrado ", setorId));
	}

}