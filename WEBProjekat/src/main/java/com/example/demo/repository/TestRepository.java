package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajpredmet;
import model.Ajtest;

public interface TestRepository extends JpaRepository<Ajtest,Integer> {
	List<Ajtest> findByAjpredmet(Ajpredmet p);
	Ajtest findByImeTesta(String imeTesta);
}
