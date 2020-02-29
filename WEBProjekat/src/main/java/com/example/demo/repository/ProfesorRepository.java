package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Ajpredmet;
import model.Ajprofesor;

public interface ProfesorRepository  extends JpaRepository<Ajprofesor,Integer>{
	Ajprofesor findByUsername(String username);

}
