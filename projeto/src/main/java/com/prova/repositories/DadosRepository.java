package com.prova.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prova.entities.Medicos;


public interface DadosRepository extends JpaRepository<Medicos, Integer>{

}
