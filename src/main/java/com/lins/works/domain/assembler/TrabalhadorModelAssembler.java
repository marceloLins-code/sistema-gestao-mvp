package com.lins.works.domain.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lins.works.domain.entity.Trabalhador;
import com.lins.works.modelDTO.TrabalhadorModel;
@Component
public class TrabalhadorModelAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public TrabalhadorModel toModel(Trabalhador trabalhador) {
		 return mapper.map(trabalhador, TrabalhadorModel.class );
	}

	public List<TrabalhadorModel> toCollectionModel(List<Trabalhador> trabalhador) {
		return trabalhador.stream()
				.map(trab -> toModel(trab))
				.collect(Collectors.toList());

	}

}
