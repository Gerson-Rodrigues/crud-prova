package com.prova.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


import com.prova.entities.Medicos;
import com.prova.repositories.IDadosRepository;
import com.prova.requests.DadosGetResponse;
import com.prova.requests.DadosPostRequest;
import com.prova.requests.DadosPutRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.ApiOperation;

@Controller
@Transactional
public class DadosController {

	@Autowired
	private IDadosRepository dadosRepository;
	
	private static final String ENDPOINT = "/api/medicos";
	
	@ApiOperation("Serviço de Cadastro de Pacientes")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String>post(@RequestBody DadosPostRequest request){
		try {
			Medicos d = new Medicos();
			d.setNome(request.getNome());
			d.setCrm(request.getCrm());
			d.setTelefone(request.getTelefone());
			d.setTipo(request.getTipo());
			
			dadosRepository.save(d);
			
			return ResponseEntity.status(HttpStatus.OK).body("Feitooooo!!!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Deu Ruimm"+e.getMessage());
		}
	}
	@ApiOperation("Serviço que Busca todos os Médicos")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<DadosGetResponse>> get(){
		List<DadosGetResponse> response = new ArrayList<DadosGetResponse>();
		
		for(Medicos p: dadosRepository.findAll()) {
			DadosGetResponse item = new DadosGetResponse();
			
			item.setId_medico(p.getId_medico());
			item.setNome(p.getNome());
			item.setCrm(p.getCrm());
			item.setTelefone(p.getTelefone());
			item.setTipo(p.getTipo());
			
			response.add(item);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@ApiOperation("Serviço que Busca um Médico apenas")
	@RequestMapping(value = ENDPOINT+"/{id_medico}", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<DadosGetResponse> getById(@PathVariable("id_medico")Integer id_medico){
		
		Optional<Medicos> item = dadosRepository.findById(id_medico);
		
		if(item.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else {
			DadosGetResponse response = new DadosGetResponse();
			Medicos p = item.get();
			
			response.setId_medico(p.getId_medico());
			response.setNome(p.getNome());
			response.setCrm(p.getCrm());
			response.setTelefone(p.getTelefone());
			response.setTipo(p.getTipo());
			
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}
	
	@ApiOperation("Serviço para exclusão de um Médico")
	@RequestMapping(value = ENDPOINT+"/{id_medico}", method = RequestMethod.DELETE)
	@CrossOrigin
	public ResponseEntity<String>delete(@PathVariable("id_medico")Integer id_medico){
		try {
			Optional<Medicos> item = dadosRepository.findById(id_medico);
			if(item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("ID não localizado, tente de novo");
			}else {
				Medicos pac = item.get();
				dadosRepository.delete(pac);
				return ResponseEntity.status(HttpStatus.OK)
						.body("Dados do medico excluido com sucesso!!!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Deu ERRO"+e.getMessage());
		}
	}
	
	@ApiOperation("Serviço para atualizar os Dados")
	@RequestMapping(value = ENDPOINT, method = RequestMethod.PUT)
	@CrossOrigin
	public ResponseEntity<String>put(@RequestBody DadosPutRequest request){
		try {
			Optional<Medicos> item = dadosRepository.findById(request.getId_medico());
			
			if(item.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Não achei o medico solicitado!!");
			}else {
				Medicos d = item.get();
				d.setId_medico(request.getId_medico());
				d.setNome(request.getNome());
				d.setCrm(request.getCrm());
				d.setTelefone(request.getTelefone());
				d.setTipo(request.getTipo());
				
				dadosRepository.save(d);
				return ResponseEntity.status(HttpStatus.OK)
						.body("Dados atualizados com sucesso");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Deu RUIM, "+e.getMessage());
		}
	}
}
