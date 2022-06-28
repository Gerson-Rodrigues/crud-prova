package com.prova.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosPutRequest {

	private Integer id_medico;
	private String nome;
	private String crm;
	private String telefone;
	private String tipo;
	
}
