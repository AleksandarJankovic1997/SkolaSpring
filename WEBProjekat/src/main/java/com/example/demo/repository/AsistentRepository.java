package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajasistent;
import model.Ajpredmet;

public interface AsistentRepository extends JpaRepository<Ajasistent,Integer> {
	Ajasistent findByUsername(String username);
}
