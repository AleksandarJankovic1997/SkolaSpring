package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajobavestenje;
import model.Ajpredmet;

public interface ObavestenjeRepository extends JpaRepository<Ajobavestenje,Integer> {
	List<Ajobavestenje> findByAjpredmet(Ajpredmet p); 
}
