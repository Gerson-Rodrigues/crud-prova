package com.prova;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.prova.entities.Medicos;
import com.prova.repositories.DadosRepository;
import com.prova.service.MedicosReportService;
import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
@EnableWebMvc
@RestController
@EnableAutoConfiguration
public class GersonApplication {

	@Autowired
	private DadosRepository repository;
	@Autowired
	private MedicosReportService service;

	@GetMapping("/pegaMedicos")
	public List<Medicos> getMedicos(){
		return repository.findAll();
	}

	@GetMapping("/jasper/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException{
		return service.exportReport(format);
	}
	public static void main(String[] args) {
		SpringApplication.run(GersonApplication.class, args);
	}

}
