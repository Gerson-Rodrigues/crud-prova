package com.prova.requests;

import lombok.Data;

@Data
public class EmailPostRequest {

	private String de;
	private String para;
	private String assunto;
	private String texto;
	
}
