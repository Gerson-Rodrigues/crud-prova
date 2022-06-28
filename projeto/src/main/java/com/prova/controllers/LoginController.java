package com.prova.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prova.entities.Usuario;
import com.prova.repositories.IUsuarioRepository;
import com.prova.requests.LoginPostRequest;
import com.prova.security.MD5Cryptography;
import com.prova.security.TokenSecurity;
import io.swagger.annotations.ApiOperation;
//import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@Transactional
public class LoginController {

	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	private static final String ENDPOINT = "/api/login";
	
	@ApiOperation("Serviço para autenticação de Usuário")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String>post(@RequestBody LoginPostRequest request){
		try {
			//pesquisar no banco de dados o usuario
			//através do login e senha
			Usuario user = usuarioRepository
					.findByLoginAndSenha(request.getLogin(), 
					MD5Cryptography.encrypt(request.getSenha()));
			
			if(user != null) {
				//return ResponseEntity.status(HttpStatus.OK).body("Usuário liberado o acesso!!!!");
				return ResponseEntity.status(HttpStatus.OK).body(TokenSecurity.generateToken(user.getLogin()));
				
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
						.body("ACESSO NEGADO!!!!!!!!!!!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}		
	}
}