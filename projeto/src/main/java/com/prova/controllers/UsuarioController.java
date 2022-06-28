package com.prova.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prova.entities.Usuario;
import com.prova.repositories.IUsuarioRepository;
import com.prova.requests.UsuarioGetResponse;
import io.swagger.annotations.ApiOperation;


@Controller
@Transactional
public class UsuarioController {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	private static final String ENDPOINT = "/api/usuario";
	
	@ApiOperation("Serviço para exclusão de um Usuário")
	@RequestMapping(value = ENDPOINT + "/{idusuario}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<String>delete(@PathVariable("idusuario")Integer idusuario){
		try {
			Optional<Usuario> item = usuarioRepository.findById(idusuario);
			if(item.isEmpty()){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Usuário não encontrado, verifique o código digitado");
			}else {
				Usuario prod = item.get();
				usuarioRepository.delete(prod);
				return ResponseEntity.status(HttpStatus.OK)
						.body("Usuário excluído com sucesso!!!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("ERROR: "+e.getMessage());
		}
	}
	
	@ApiOperation("Serviço para consulta de Usuários")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<UsuarioGetResponse>>get(){
		List<UsuarioGetResponse> response = new ArrayList<UsuarioGetResponse>();
		for(Usuario p: usuarioRepository.findAll()) {
			UsuarioGetResponse item = new UsuarioGetResponse();
			
			item.setIdusuario(p.getIdusuario());
			item.setNome(p.getNome());
			item.setLogin(p.getLogin());
			item.setSenha(p.getSenha());
			
			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
