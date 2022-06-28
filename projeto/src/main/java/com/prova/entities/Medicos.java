package com.prova.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="medicos")
public class Medicos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_medico")
	private Integer id_medico;
	@Column(name="nome", length=150, nullable = false)
	private String nome;
	@Column(name="crm", nullable = false)
	private String crm;
	@Column(name="telefone", nullable = false)
	private String telefone;
	@Column(name="tipo",  nullable = false)
	private String tipo;
}













