package com.lins.works.global.exceptionHandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Erro {
	
	private LocalDateTime dataHora;
	private String msg;	

}
