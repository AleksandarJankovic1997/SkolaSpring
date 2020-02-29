package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajasistent;
import model.Ajpredmet;
import model.Ajprofesor;
import model.Ajstudent;

public interface PredmetRepository extends JpaRepository<Ajpredmet,Integer>{
	List<Ajpredmet> findByAjprofesor(Ajprofesor p);
	
	List<Ajpredmet> findByAjasistent(Ajasistent a);
	
	Ajpredmet findByNaziv(String ime);
}
