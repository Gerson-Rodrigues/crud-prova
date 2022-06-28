package com.prova.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioGetResponse {

	private Integer idusuario;
	private String nome;
	private String login;
	private String senha;
}
