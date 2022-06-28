package com.prova.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.prova.entities.Medicos;
import com.prova.repositories.DadosRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class MedicosReportService {

	@Autowired
	private DadosRepository repository;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException{
		
	String path = "D:\\springboot\\projeto\\Arquivos"; 
		//criar uma lista de empragados vazia para receber os empregados do banco
		List<Medicos> empregados = repository.findAll();
		
		//vamos abrir e compilar 
		File file = ResourceUtils.getFile("classpath:medicos.jrxml");
		
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		
		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(empregados);
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Criado por","Gerson Rodrigues");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
		
		if(reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path+"\\medicos.html");
		}
		if(reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\medicos.pdf");			
		}
		return "Report gerado com SUCESSO!!!";
	}
}
