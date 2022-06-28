package com.prova.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prova.entities.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}